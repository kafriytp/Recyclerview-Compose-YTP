package com.eunidev.compose.recyclerviewytp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eunidev.compose.recyclerviewytp.model.People
import com.eunidev.compose.recyclerviewytp.ui.theme.RecyclerviewYTPTheme
import com.google.accompanist.glide.rememberGlidePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclerviewYTPTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainLayout()
                }
            }
        }
    }
}

@Composable
fun MainLayout() {

    // Sample Data
    val peopleList = arrayListOf(
        People("Lee Seo-jeong", "07 Januari 2000", "https://i.pinimg.com/originals/13/67/ec/1367ec0471dad18c39d639b297d50797.jpg"),
        People("Lee Da-bin", "01 Agustus 1996", "https://upload.wikimedia.org/wikipedia/commons/a/a5/180105_%EB%AE%A4%EC%A7%81%EB%B1%85%ED%81%AC_%EB%AF%B8%EB%8B%88%ED%8C%AC%EB%AF%B8%ED%8C%85_%282%29.jpg"),
        People("Jang Won-young", "31 Agustus 2004", "https://i.pinimg.com/736x/98/86/41/9886411a830f263dd4f72edab7f31ee6.jpg"),
        People("Kim Min-ju", "05 Februari 2001", "https://udahketemu.com/wp-content/uploads/2019/05/shibal-668x393.jpg"),
        People("Kim So-hyun", "04 Juni 1999", "https://cdn0-production-images-kly.akamaized.net/Qx8QnFYNaB9VDs41X7S83v5Kc-g=/640x640/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/995270/original/d2b9774eacd5fbf079adae58a4836ba8-062610300_1442801071-kim-so-hyun.jpg"),
        People("Kim Yoo-jung", "22 September 1999", "https://blue.kumparan.com/image/upload/fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/v1600750462/phefmc6hiigr3k4qy2tr.jpg"),
        People("Jung Eun-bi", "30 Mei 1997", "https://thumb.intipseleb.com/media/frontend/thumbs3/2020/11/10/5faa7609e57e6-eunha-gfriend_663_372.jpeg"),
        People("Kim Si-hyeon", "05 Agustus 1999", "https://i.pinimg.com/originals/05/f1/70/05f170cb4421001926a84de04bcedcb5.jpg"),
        People("Lee Lu-da", "06 Maret 1997", "https://i.imgur.com/NeSVM6O.jpeg"),
    )

    // Recyclerview
    LazyColumn {

        // Add Item
        items(peopleList.size) { index ->
            PeopleCardLayout(context = LocalContext.current, people = peopleList[index])
        }
    }
}

@Composable
fun PeopleCardLayout(context: Context, people: People) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colors.surface)
        .clickable {
            Toast
                .makeText(context, people.toString(), Toast.LENGTH_SHORT)
                .show()
        }
        .padding(16.dp)
    ) {

        // Background Image
        Surface (
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image
            Image(painter = rememberGlidePainter(
                request = people.url,
            ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            // People Name
            Text(text = people.name, fontWeight = FontWeight.Bold)

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = people.birth, style = MaterialTheme.typography.body2)
            }
        }
    }
}
