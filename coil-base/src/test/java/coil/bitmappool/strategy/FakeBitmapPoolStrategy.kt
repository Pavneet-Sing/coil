package coil.bitmappool.strategy

import android.graphics.Bitmap

@OptIn(ExperimentalStdlibApi::class)
class FakeBitmapPoolStrategy : BitmapPoolStrategy {

    val bitmaps = ArrayDeque<Bitmap>()

    var numRemoves = 0
    var numPuts = 0

    override fun put(bitmap: Bitmap) {
        numPuts++
        bitmaps += bitmap
    }

    override fun get(width: Int, height: Int, config: Bitmap.Config): Bitmap? {
        return if (bitmaps.isEmpty()) null else bitmaps.removeLast()
    }

    override fun removeLast(): Bitmap? {
        return if (bitmaps.isEmpty()) null else bitmaps.removeLast().also { numRemoves++ }
    }

    override fun logBitmap(bitmap: Bitmap) = ""

    override fun logBitmap(width: Int, height: Int, config: Bitmap.Config) = ""
}
