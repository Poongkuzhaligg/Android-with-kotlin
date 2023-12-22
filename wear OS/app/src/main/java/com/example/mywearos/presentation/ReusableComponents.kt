import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

val contentModifier = Modifier
    .fillMaxWidth()
    .padding(bottom = 8.dp)

@Composable
fun TextSpace(modifier: Modifier = Modifier, contents: String) {
    Text(text = contents, )
}