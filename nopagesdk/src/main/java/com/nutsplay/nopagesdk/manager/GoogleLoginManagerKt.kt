package com.nutsplay.nopagesdk.manager

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID


/**
 * Created by frankma on 2025/6/9 12:50
 * Email: frankma9103@gmail.com
 * Desc:
 */
object GoogleLoginManagerKt {
//    public fun getInstance():GoogleLoginManagerKt{
//        return GoogleLoginManagerKt()
//    }

    val TAG = "GoogleLoginManagerKt"

    suspend fun login(context: Activity, WEB_CLIENT_ID: String, nonceString: String) {
//        val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption.Builder()
//            .setServerClientId(WEB_CLIENT_ID)
//            .setNonce(nonceString)
//        .build()


        val googleIdOption: GetGoogleIdOption =
            GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(true)
                .setServerClientId(WEB_CLIENT_ID).setAutoSelectEnabled(true).setNonce(nonceString)
                .build()


        val request: GetCredentialRequest =
            GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()


        GlobalScope.launch {
            try {
                val credentialManager = CredentialManager.create(context)
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                handleSignIn(result)
            } catch (e: GetCredentialException) {
                handleFailure(e)
            }
        }

//        coroutineScope.launch {
//            try {
//                val credentialManager = CredentialManager.create(context)
//                val result = credentialManager.getCredential(
//                    request = request,
//                    context = context,
//                )
//                handleSignIn(result)
//            } catch (e: GetCredentialException) {
//                handleFailure(e)
//            }
//        }
    }

    fun handleFailure(e: GetCredentialException) {
        TODO("Not yet implemented")
    }

    fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.

        when (val credential = result.credential) {

            // Passkey credential
            is PublicKeyCredential -> {
                // Share responseJson such as a GetCredentialResponse on your server to
                // validate and authenticate
                val responseJson = credential.authenticationResponseJson
            }

            // Password credential
            is PasswordCredential -> {
                // Send ID and password to your server to validate and authenticate.
                val username = credential.id
                val password = credential.password

            }

            // GoogleIdToken credential
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract the ID to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        // You can use the members of googleIdTokenCredential directly for UX
                        // purposes, but don't use them to store or control access to user
                        // data. For that you first need to validate the token:
                        // pass googleIdTokenCredential.getIdToken() to the backend server.

//                        googleIdTokenCredential.idToken
//                        GoogleIdTokenVerifier verifier = ... // see validation instructions
//                        GoogleIdToken idToken = verifier.verify(idTokenString);
                        // To get a stable account identifier (e.g. for storing user data),
                        // use the subject ID:
//                        idToken.getPayload().getSubject()
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }
}