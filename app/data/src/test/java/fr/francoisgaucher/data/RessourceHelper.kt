package fr.francoisgaucher.data

import java.io.InputStream


class RessourceHelper {
    companion object {
        fun readContentFromJsonAsset(fileName: String): String? = RessourceHelper::class.java.classLoader?.getResourceAsStream(fileName)?.bufferedReader()?.readText()
        fun getFileInputStreamFromJsonAsset(fileName: String): InputStream? = RessourceHelper::class.java.classLoader?.getResourceAsStream(fileName)
    }
}
