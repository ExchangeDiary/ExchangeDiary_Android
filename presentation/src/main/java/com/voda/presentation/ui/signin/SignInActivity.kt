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

    private fun signout() {
        auth.signOut()
    }

    private fun revoke() {
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
        Timber.tag(TAG).e("metadata >> ${user.metadata}")
        Timber.tag(TAG).e("photoUrl >> ${user.photoUrl}")
        Timber.tag(TAG).e("phoneNumber >> ${user.phoneNumber}")

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onClickGoogle(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        requestGoogleLogin.launch(signInIntent)
    }

    fun onClickKakao(view: View) {}

    companion object {
        private val TAG: String = SignInActivity::class.java.simpleName
    }
}