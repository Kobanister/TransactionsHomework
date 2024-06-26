package com.transactions.homework.data.source.local.util

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys.AES256_GCM_SPEC
import java.io.File
import java.security.SecureRandom

private const val PASSPHRASE_FILE = "DataBasePassphrase.bin"

class DataBaseEncryptionHelper(private val context: Context) {
    fun getPassphrase(): ByteArray {
        val file = File(context.filesDir, PASSPHRASE_FILE)
        val encryptedFile = EncryptedFile.Builder(
            context,
            file,
            MasterKey.Builder(context).setKeyGenParameterSpec(AES256_GCM_SPEC).build(),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        return if (file.exists()) {
            encryptedFile.openFileInput().use { it.readBytes() }
        } else {
            generatePassphrase().also { passPhrase ->
                encryptedFile.openFileOutput().use { it.write(passPhrase) }
            }
        }
    }

    private fun generatePassphrase(): ByteArray {
        val random = SecureRandom.getInstanceStrong()
        val result = ByteArray(32)
        random.nextBytes(result)
        while (result.contains(0)) {
            random.nextBytes(result)
        }
        return result
    }
}
