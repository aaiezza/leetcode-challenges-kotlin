data class Point(val x: X, val y: Y) {
    data class X(val value: Double) {
        infix fun riseTo(y: Y) = Point(this, y)
    }

    data class Y(val value: Double)

    override fun toString(): String =
        "(%.1f, %.1f)".format(x.value, y.value)
}

open class FlatLine(
    val slope: Slope,
    val yIntercept: YIntercept,
    val xIntercept: XIntercept = slope.xInterceptFrom(Point.X(0.0) riseTo Point.Y(yIntercept.value))
) {
    companion object {
        fun createFlatLineFromTwoPoints(point1: Point, point2: Point): FlatLine {
            require(point1 != point2) { "A line must pass through two _different_ points." }

            return if (point1.x == point2.x) {
                Vertical(point1.x)
            } else {
                val slope = Slope((point2.y.value - point1.y.value) / (point2.x.value - point1.x.value))
                FlatLine(
                    slope = slope,
                    yIntercept = slope.yInterceptFrom(point1)
                )
            }
        }
    }

    data class Slope(val value: Double) {
        fun yInterceptFrom(point: Point) = YIntercept(point.y.value - (value * point.x.value))
        fun xInterceptFrom(point: Point) = XIntercept(point.x.value - (point.y.value / value))
    }

    data class YIntercept(val value: Double)
    data class XIntercept(val value: Double)

    override fun toString(): String =
        "y=%.1fx%+.1f".format(slope.value, yIntercept.value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FlatLine) return false

        if (slope != other.slope) return false
        if (yIntercept != other.yIntercept) return false
        if (xIntercept != other.xIntercept) return false

        return true
    }

    override fun hashCode(): Int {
        var result = slope.hashCode()
        result = 31 * result + yIntercept.hashCode()
        result = 31 * result + xIntercept.hashCode()
        return result
    }

    class Vertical(x: Point.X) :
        FlatLine(
            slope = Slope(Double.POSITIVE_INFINITY),
            yIntercept = YIntercept(Double.NaN),
            xIntercept = XIntercept(x.value)
        ) {
        override fun toString(): String = "x=%.1f".format(xIntercept.value)
    }
}

class FlatLineIntersectionFinder {
    fun findIntersection(line1: FlatLine, line2: FlatLine): Point {
        require(line1 != line2) { "These are the same line. They intersect everywhere!" }
        require(line1.slope != line2.slope) { "Parallel lines do not intersect." }

        val x: Point.X = (listOf(line1, line2)
            .firstOrNull { it is FlatLine.Vertical }
            ?.let { it.xIntercept.value }
            ?: ((line2.yIntercept.value - line1.yIntercept.value) /
                    (line1.slope.value - line2.slope.value)))
            .let { if(it == -0.0) 0.0 else it }
            .let { Point.X(it) }

        val y: Point.Y = if (line1 is FlatLine.Vertical) {
            Point.Y((line2.slope.value * x.value) + line2.yIntercept.value)
        } else {
            Point.Y((line1.slope.value * x.value) + line1.yIntercept.value)
        }

        return x riseTo y
    }
}
