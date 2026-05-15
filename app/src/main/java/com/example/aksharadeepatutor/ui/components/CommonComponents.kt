package com.example.aksharadeepatutor.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.model.SubjectPerformance
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TutorCard(
    title: String,
    subtitle: String,
    accent: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                color = accent,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )
        }
    }
}

@Composable
fun SubjectProgressRow(
    subject: String,
    progress: Int,
    score: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(subject, fontWeight = FontWeight.Bold)
            Text("$progress% syllabus | $score% quiz")
        }

        LinearProgressIndicator(
            progress = { progress / 100f },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RadarChart(
    items: List<SubjectPerformance>,
    modifier: Modifier = Modifier
) {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val gridColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.18f)

    Column(modifier = modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = size.minDimension * 0.35f

            repeat(4) { ring ->
                drawCircle(
                    color = gridColor,
                    radius = radius * (ring + 1) / 4f,
                    center = center,
                    style = Stroke(width = 2f)
                )
            }

            if (items.isNotEmpty()) {
                val axisPoints = items.indices.map { index ->
                    val angle = -PI / 2 + index * 2 * PI / items.size
                    Offset(
                        x = center.x + cos(angle).toFloat() * radius,
                        y = center.y + sin(angle).toFloat() * radius
                    )
                }

                axisPoints.forEach {
                    drawLine(
                        color = gridColor,
                        start = center,
                        end = it,
                        strokeWidth = 2f
                    )
                }

                val points = items.mapIndexed { index, item ->
                    val angle = -PI / 2 + index * 2 * PI / items.size
                    val value = ((if (item.averageScore > 0) item.averageScore else item.progress)
                        .coerceIn(0, 100)) / 100f

                    Offset(
                        x = center.x + cos(angle).toFloat() * radius * value,
                        y = center.y + sin(angle).toFloat() * radius * value
                    )
                }

                val path = Path()
                points.forEachIndexed { index, point ->
                    if (index == 0) {
                        path.moveTo(point.x, point.y)
                    } else {
                        path.lineTo(point.x, point.y)
                    }
                }
                path.close()

                drawPath(path, primary.copy(alpha = 0.28f))
                drawPath(path, secondary, style = Stroke(width = 4f))
                points.forEach {
                    drawCircle(primary, radius = 7f, center = it)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach {
                Text(
                    text = it.subject,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
