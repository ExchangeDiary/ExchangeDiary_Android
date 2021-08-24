package com.voda.presentation.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.voda.presentation.R
import com.voda.presentation.ui.main.MainActivity
import timber.log.Timber


class SignInActivity : AppCompatActivity() {
    //Firebase Auth
    private lateinit var auth: FirebaseAuth

    //Google Api Client
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun googleSignOut() {
        auth.signOut()
    }

    private fun googleRevoke() {
        auth.currentUser?.delete()
    }

    private val requestGoogleLogin = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)
            Timber.tag(TAG).d("firebaseAuthWithGoogle:%s", account.id)
            firebaseAuthWithGoogle(account.idToken ?: return@registerForActivityResult)
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Timber.tag(TAG).w(e, "Google sign in failed")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.tag(TAG).d("signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.tag(TAG).w(task.exception, "signInWithCredential:failure")
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) return

        Timber.tag(TAG).e("email >> ${user.email}")
        Timber.tag(TAG).e("displayName >> ${user.displayName}")
        Timber.tag(TAG).e("photoUrl >> ${user.photoUrl}")

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onClickGoogle(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        requestGoogleLogin.launch(signInIntent)
    }

    fun onClickKakao(view: View) {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { accessToken, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError())
                        requestKakaoLogin()
                    else
                        Timber.tag(TAG).e(error, "accessToken 기타에러")
                } else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨) - 사용자 정보 얻음
                    Timber.tag(TAG).e("id >> ${accessToken?.id}")
                    Timber.tag(TAG).e("만료 남은 시간(초) >> ${accessToken?.expiresIn}")
                    getKakaoUser()
                }
            }
        } else {
            requestKakaoLogin()
        }
    }

    fun getKakaoUser(){
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Timber.tag(TAG).e(error,"사용자 정보 요청 실패")
            }
            else if (user != null) {
                Timber.tag(TAG).e("사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
            }
        }
    }

    fun requestKakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            Timber.tag(TAG).i("앱으로 로그인")
            UserApiClient.instance.loginWithKakaoTalk(
                this,
                callback = kakaoLoginCallback
            ) // 카카오톡으로 로그인
        } else {
            Timber.tag(TAG).i("계정으로 로그인")
            UserApiClient.instance.loginWithKakaoAccount(
                this,
                callback = kakaoLoginCallback
            ) // 카카오톡으로 로그인
        }
    }

    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Timber.tag(TAG).e(error, "로그인 실패")
        } else if (token != null) {
            Timber.tag(TAG).i("로그인 성공 ${token.accessToken}")
            getKakaoUser()
        }
    }

    private val kakaoLogoutCallback: (Throwable?) -> Unit = { error ->
        if (error != null) {
            Timber.tag(TAG).e(error, "로그아웃 실패. SDK에서 토큰 삭제됨")
        } else {
            Timber.tag(TAG).e("로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }

    fun kakaoSignOut() {
        UserApiClient.instance.logout(callback = kakaoLogoutCallback)
    }

    fun kakaoRevoke() {
        UserApiClient.instance.unlink(callback = kakaoLogoutCallback)
    }

    companion object {
        private val TAG: String = SignInActivity::class.java.simpleName
    }
}
