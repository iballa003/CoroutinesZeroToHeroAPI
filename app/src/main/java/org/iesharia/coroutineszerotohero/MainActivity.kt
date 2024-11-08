package org.iesharia.coroutineszerotohero

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.iesharia.coroutineszerotohero.ui.theme.CoroutinesZeroToHeroTheme
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val retrofit = RetrofitHelper.getInstance()
        val userList = mutableStateListOf<UserItemResponse>()

        lifecycleScope.launch(Dispatchers.IO) {
            val resultado: Response<List<UserItemResponse>> = retrofit.getUsers()  // Asegúrate de que getUsers devuelva Response<List<UserItemResponse>>
            withContext(Dispatchers.Main) {
                if (resultado.isSuccessful && resultado.body() != null) {
                    userList.addAll(resultado.body()!!)
                    Log.i("ejemplo1", "$resultado")
                    Toast.makeText(this@MainActivity, "Funciona", Toast.LENGTH_SHORT).show()
                    Log.i("ejemplo2", resultado.body().toString())
                }
            }
        }

        setContent {
            CoroutinesZeroToHeroTheme {
                Surface {
                    UserList(userList)
                }
            }
        }
    }
}

@Composable
fun UserList(users: List<UserItemResponse>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

        ) {
            Text(text = "Id", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(text = "Nombre", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(text = "Usuario", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(text = "Email", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(users) { user ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Log.i("DAM2",user.id)
                    Text(text = user.id.toString(), modifier = Modifier.weight(1f))
                    Text(text = user.name, modifier = Modifier.weight(1f))
                    Text(text = user.username, modifier = Modifier.weight(1f))
                    Text(text = user.email, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

