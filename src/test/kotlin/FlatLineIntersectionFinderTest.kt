import LineIntersectionTest.Companion.expectedFailingInput
import LineIntersectionTest.Companion.input
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.*

class FlatLineIntersectionFinderTest {
    private lateinit var subject: FlatLineIntersectionFinder

    @BeforeEach
    fun setUp() {
        subject = FlatLineIntersectionFinder()
    }

    @Test
    fun `should fail to instantiate line from sample points`() {
        val point1 = x(1.0) riseTo y(4.0)
        val point2 = x(1.0) riseTo y(4.0)

        assertThrows<IllegalArgumentException> { FlatLine.createFlatLineFromTwoPoints(point1, point2) }
            .run {
                assertThat(message).isEqualTo("A line must pass through two _different_ points.")
            }
    }

    @Test
    fun `should instantiate the expected line from sample points`() {
        val point1 = x(1.0) riseTo y(4.0)
        val point2 = x(2.0) riseTo y(8.0)

        val line = FlatLine.createFlatLineFromTwoPoints(point1, point2)
        assertThat(line).isEqualTo(FLAT_LINE(slope(4), yInt(0)))
    }

    @Test
    fun `should instantiate the expected vertical line from sample points`() {
        val point1 = x(2.0) riseTo y(4.0)
        val point2 = x(2.0) riseTo y(8.0)

        val line = FlatLine.createFlatLineFromTwoPoints(point1, point2)
        assertThat(line).isEqualTo(VERTICAL_LINE(x(2)))
    }

    @TestFactory
    fun `should return expected line intersections`() = listOf(
        input(
            "Two regular lines",
            FLAT_LINE(slope(5), yInt(-40)),
            FLAT_LINE(slope(0.4), yInt(6)),
            x(10), y(10)
        ),
        input(
            "Two more regular lines",
            FLAT_LINE(slope(0), yInt(1)),
            FLAT_LINE(slope(2), yInt(1)),
            x(0), y(1)
        ),
        input(
            "Temperature Cº and Fº",
            FLAT_LINE(slope(9.0 / 5.0), yInt(32)),
            FLAT_LINE(slope(5.0 / 9.0), yInt(-160.0 / 9.0)),
            x(-40), y(-40)
        ),
        input(
            "Two regular lines, 1 is steep",
            FLAT_LINE(slope(24), yInt(0)),
            FLAT_LINE(slope(-0.5), yInt(24.5)),
            x(1), y(24)
        ),
        input(
            "Line 1 is vertical on x=2",
            VERTICAL_LINE(x(2)),
            FLAT_LINE(slope(0.5), yInt(1)),
            x(2), y(2)
        ),
        input(
            "Line 2 is vertical on x=2",
            FLAT_LINE(slope(0.5), yInt(1)),
            VERTICAL_LINE(x(2)),
            x(2), y(2)
        ),
        input(
            "Line 1 is vertical on x=0",
            VERTICAL_LINE(x(0)),
            FLAT_LINE(slope(0.5), yInt(1)),
            x(0), y(1)
        ),
        input(
            "Line 1 is vertical on x=-2",
            VERTICAL_LINE(x(-2)),
            FLAT_LINE(slope(0.5), yInt(1)),
            x(-2), y(0)
        ),
        input(
            "Line 1 is horizontal on y=0",
            HORIZONTAL_LINE(y(0)),
            FLAT_LINE(slope(0.5), yInt(1)),
            x(-2), y(0)
        ),
        input(
            "Line 1 is horizontal on y=3",
            HORIZONTAL_LINE(y(3)),
            FLAT_LINE(slope(0.5), yInt(1)),
            x(4), y(3)
        ),
        input(
            "Line 1 is horizontal on y=-3",
            HORIZONTAL_LINE(y(-3)),
            FLAT_LINE(slope(0.5), yInt(1)),
            x(-8), y(-3)
        ),
        input(
            "Line 2 is horizontal on y=-3",
            FLAT_LINE(slope(0.5), yInt(1)),
            HORIZONTAL_LINE(y(-3)),
            x(-8), y(-3)
        ),
        input(
            "Line 1 is horizontal on y=3, and Line 2 is vertical on x=2",
            HORIZONTAL_LINE(y(3)),
            VERTICAL_LINE(x(2)),
            x(2), y(3)
        ),
    ).map {
        DynamicTest.dynamicTest("${it.line1} & ${it.line2} → ${it.expected} | ${it.description}") {
            assertThat(subject.findIntersection(it.line1, it.line2)).isEqualTo(it.expected)
        }
    }

    @TestFactory
    fun `should return exceptions for non-existent line intersections`() = listOf(
        expectedFailingInput(
            "Two, parallel, vertical lines, x=-1 & x=2",
            VERTICAL_LINE(x(1)),
            VERTICAL_LINE(x(2)),
        ),
        expectedFailingInput(
            "Two. parallel, horizontal lines, y=-1 & y=3",
            HORIZONTAL_LINE(y(1)),
            HORIZONTAL_LINE(y(3)),
        ),
        expectedFailingInput(
            "Line 1 is parallel with Line 2",
            FLAT_LINE(slope(2), yInt(0)),
            FLAT_LINE(slope(2), yInt(4)),
        ),
        expectedFailingInput(
            "Line 1 and Line 2 are the same",
            FLAT_LINE(slope(3), yInt(1)),
            FLAT_LINE(slope(3), yInt(1)),
        ),
    ).map {
        DynamicTest.dynamicTest("${it.line1} & ${it.line2} → No Intersection | ${it.description}") {
            assertThrows<IllegalArgumentException> { subject.findIntersection(it.line1, it.line2) }
        }
    }
}

private data class LineIntersectionTest(
    val description: String,
    val line1: FlatLine,
    val line2: FlatLine,
    val expected: Point?
) {
    companion object {
        fun input(
            description: String,
            line1: FlatLine,
            line2: FlatLine,
            expectedX: Point.X,
            expectedY: Point.Y,
        ) = LineIntersectionTest(
            description = description,
            line1 = line1,
            line2 = line2,
            expected = expectedX riseTo expectedY
        )

        fun expectedFailingInput(
            description: String,
            line1: FlatLine,
            line2: FlatLine,
        ) = LineIntersectionTest(
            description = description,
            line1 = line1,
            line2 = line2,
            expected = null
        )
    }
}

private fun x(value: Number) = Point.X(value.toDouble())
private fun y(value: Number) = Point.Y(value.toDouble())
private fun slope(value: Number) = FlatLine.Slope(value.toDouble())
private fun yInt(value: Number) = FlatLine.YIntercept(value.toDouble())

private val HORIZONTAL_LINE: (Point.Y) -> FlatLine = { FlatLine(slope = slope(0), yInt(it.value)) }
private val VERTICAL_LINE: (Point.X) -> FlatLine.Vertical = { FlatLine.Vertical(it) }
private val FLAT_LINE: (FlatLine.Slope, FlatLine.YIntercept) -> FlatLine =
    { slope, yInt -> FlatLine(slope, yInt) }
