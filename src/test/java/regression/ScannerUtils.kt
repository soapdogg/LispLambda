package regression

import java.nio.file.Paths
import java.util.*
import java.util.stream.StreamSupport
import kotlin.streams.toList

object ScannerUtils {

    fun getScannerFromFilePath(programFilePath: String): Scanner {
        return Scanner(Paths.get(programFilePath))
    }

    fun transformScannerInputToLispInput(
        scanner: Scanner
    ): String {
        return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED),
            true
        ).toList().joinToString(separator = " ")
    }

    fun scanExpected(expectedFile: String): String {
        val builder = StringBuilder()
        val scanner = getScannerFromFilePath(expectedFile)

        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            builder.append(next)
            builder.append('\n')
        }

        return builder.toString()
    }
}