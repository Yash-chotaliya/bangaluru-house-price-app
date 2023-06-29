package com.example.bengaluru_price_prediction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bengaluru_price_prediction.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val url = "https://bangaluru-house-price-model.onrender.com/predict"
    private val locations = getLocations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayadapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,locations)
        binding.location.adapter = arrayadapter

        binding.predict.setOnClickListener {

            val queue = Volley.newRequestQueue(this@MainActivity)

            val request: StringRequest =
                object : StringRequest(Method.POST, url, object : Response.Listener<String?> {
                    override fun onResponse(response: String?) {
                        try {
                            val jsonObject = JSONObject(response!!)
                            binding.price.text = jsonObject.getString("price")
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {

                        Log.e("tag", "error is " + error!!.message)
                        Toast.makeText(this@MainActivity, "Fail to update data..", Toast.LENGTH_SHORT)
                            .show()
                    }
                }) {
                    override fun getParams(): Map<String, String> {

                        val params: MutableMap<String, String> = HashMap()

                        params["bhk"] = binding.bhk.text.toString()
                        params["totalsqft"] = binding.totalSqft.text.toString()
                        params["bath"] = binding.bath.text.toString()
                        params["location"] = binding.location.selectedItem.toString()

                        return params
                    }
                }
            queue.add(request)
        }
    }

    private fun getLocations():ArrayList<String>{
        return arrayListOf("1st Block Koramangala",
            "1st Phase JP Nagar",
            "2nd Phase Judicial Layout",
            "2nd Stage Nagarbhavi",
            "5th Block Hbr Layout",
            "5th Phase JP Nagar",
            "6th Phase JP Nagar",
            "7th Phase JP Nagar",
            "8th Phase JP Nagar",
            "9th Phase JP Nagar",
            "AECS Layout",
            "Abbigere",
            "Akshaya Nagar",
            "Ambalipura",
            "Ambedkar Nagar",
            "Amruthahalli",
            "Anandapura",
            "Ananth Nagar",
            "Anekal",
            "Anjanapura",
            "Ardendale",
            "Arekere",
            "Attibele",
            "BEML Layout",
            "BTM 1st Stage",
            "BTM 2nd Stage",
            "BTM Layout",
            "Babusapalaya",
            "Badavala Nagar",
            "Balagere",
            "Banashankari",
            "Banashankari Stage II",
            "Banashankari Stage III",
            "Banashankari Stage V",
            "Banashankari Stage VI",
            "Banaswadi",
            "Banjara Layout",
            "Bannerghatta",
            "Bannerghatta Road",
            "Basapura",
            "Basavangudi",
            "Basaveshwara Nagar",
            "Battarahalli",
            "Begur",
            "Begur Road",
            "Bellandur",
            "Benson Town",
            "Bharathi Nagar",
            "Bhoganhalli",
            "Billekahalli",
            "Binny Pete",
            "Bisuvanahalli",
            "Bommanahalli",
            "Bommasandra",
            "Bommasandra Industrial Area",
            "Bommenahalli",
            "Brookefield",
            "Budigere",
            "CV Raman Nagar",
            "Chamrajpet",
            "Chandapura",
            "Channasandra",
            "Chikka Tirupathi",
            "Chikkabanavar",
            "Chikkalasandra",
            "Choodasandra",
            "Cooke Town",
            "Cox Town",
            "Cunningham Road",
            "Dairy Circle",
            "Dasanapura",
            "Dasarahalli",
            "Devanahalli",
            "Devarachikkanahalli",
            "Dodda Nekkundi",
            "Doddaballapur",
            "Doddakallasandra",
            "Doddathoguru",
            "Dodsworth Layout",
            "Domlur",
            "Dommasandra",
            "EPIP Zone",
            "Electronic City",
            "Electronic City Phase II",
            "Electronics City Phase 1",
            "Frazer Town",
            "GM Palaya",
            "Ganga Nagar",
            "Garudachar Palya",
            "Giri Nagar",
            "Gollarapalya Hosahalli",
            "Gottigere",
            "Green Glen Layout",
            "Gubbalala",
            "Gunjur",
            "Gunjur Palya",
            "HAL 2nd Stage",
            "HBR Layout",
            "HRBR Layout",
            "HSR Layout",
            "Haralur Road",
            "Harlur",
            "Hebbal",
            "Hebbal Kempapura",
            "Hegde Nagar",
            "Hennur",
            "Hennur Road",
            "Hoodi",
            "Horamavu Agara",
            "Horamavu Banaswadi",
            "Hormavu",
            "Hosa Road",
            "Hosakerehalli",
            "Hoskote",
            "Hosur Road",
            "Hulimavu",
            "ISRO Layout",
            "ITPL",
            "Iblur Village",
            "Indira Nagar",
            "JP Nagar",
            "Jakkur",
            "Jalahalli",
            "Jalahalli East",
            "Jigani",
            "Judicial Layout",
            "KR Puram",
            "Kadubeesanahalli",
            "Kadugodi",
            "Kaggadasapura",
            "Kaggalipura",
            "Kaikondrahalli",
            "Kalena Agrahara",
            "Kalkere",
            "Kalyan nagar",
            "Kambipura",
            "Kammanahalli",
            "Kammasandra",
            "Kanakapura",
            "Kanakpura Road",
            "Kannamangala",
            "Karuna Nagar",
            "Kasavanhalli",
            "Kasturi Nagar",
            "Kathriguppe",
            "Kaval Byrasandra",
            "Kenchenahalli",
            "Kengeri",
            "Kengeri Satellite Town",
            "Kereguddadahalli",
            "Kodichikkanahalli",
            "Kodigehaali",
            "Kodigehalli",
            "Kodihalli",
            "Kogilu",
            "Konanakunte",
            "Koramangala",
            "Kothannur",
            "Kothanur",
            "Kudlu",
            "Kudlu Gate",
            "Kumaraswami Layout",
            "Kundalahalli",
            "LB Shastri Nagar",
            "Laggere",
            "Lakshminarayana Pura",
            "Lingadheeranahalli",
            "Magadi Road",
            "Mahadevpura",
            "Mahalakshmi Layout",
            "Mallasandra",
            "Malleshpalya",
            "Malleshwaram",
            "Marathahalli",
            "Margondanahalli",
            "Marsur",
            "Mico Layout",
            "Munnekollal",
            "Murugeshpalya",
            "Mysore Road",
            "NGR Layout",
            "NRI Layout",
            "Nagadevanahalli",
            "Naganathapura",
            "Nagappa Reddy Layout",
            "Nagarbhavi",
            "Nagasandra",
            "Nagavara",
            "Nagavarapalya",
            "Narayanapura",
            "Neeladri Nagar",
            "Nehru Nagar",
            "OMBR Layout",
            "Old Airport Road",
            "Old Madras Road",
            "Padmanabhanagar",
            "Pai Layout",
            "Panathur",
            "Parappana Agrahara",
            "Pattandur Agrahara",
            "Poorna Pragna Layout",
            "Prithvi Layout",
            "R.T. Nagar",
            "Rachenahalli",
            "Raja Rajeshwari Nagar",
            "Rajaji Nagar",
            "Rajiv Nagar",
            "Ramagondanahalli",
            "Ramamurthy Nagar",
            "Rayasandra",
            "Sadashiva Nagar",
            "Sahakara Nagar",
            "Sanjay nagar",
            "Sarakki Nagar",
            "Sarjapur",
            "Sarjapur  Road",
            "Sarjapura - Attibele Road",
            "Sector 2 HSR Layout",
            "Sector 7 HSR Layout",
            "Seegehalli",
            "Shampura",
            "Shivaji Nagar",
            "Singasandra",
            "Somasundara Palya",
            "Sompura",
            "Sonnenahalli",
            "Subramanyapura",
            "Sultan Palaya",
            "TC Palaya",
            "Talaghattapura",
            "Thanisandra",
            "Thigalarapalya",
            "Thubarahalli",
            "Thyagaraja Nagar",
            "Tindlu",
            "Tumkur Road",
            "Ulsoor",
            "Uttarahalli",
            "Varthur",
            "Varthur Road",
            "Vasanthapura",
            "Vidyaranyapura",
            "Vijayanagar",
            "Vishveshwarya Layout",
            "Vishwapriya Layout",
            "Whitefield",
            "Yelachenahalli",
            "Yelahanka",
            "Yelahanka New Town",
            "Yelenahalli",
            "Yeshwanthpur",
            "other")
    }
}