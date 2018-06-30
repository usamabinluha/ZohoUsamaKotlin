package `in`.usamabinluha.www.zohousamakotlin.ui

import `in`.usamabinluha.www.zohousamakotlin.R
import `in`.usamabinluha.www.zohousamakotlin.R.attr.background
import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val id :TextView = view.findViewById(R.id.id)
    private val first_name: TextView = view.findViewById(R.id.first_name)
    private val last_name: TextView = view.findViewById(R.id.last_name)
    private val avatar: ImageView = view.findViewById(R.id.avatar)
    private val magnitudeCircle: GradientDrawable = id.background as GradientDrawable
    private var user: User? = null

    fun bind(user: User?) {
        if (user == null) {
            val resources = itemView.resources
            first_name.text = resources.getString(R.string.loading)
            last_name.visibility = View.GONE
            avatar.visibility = View.GONE
        } else {
            showUserData(user)
        }
    }

    private fun showUserData(user: User) {
        this.user = user
        first_name.text = user.first_name
        id.text = user.id.toString();
        val magnitudeColor = getMagnitudeColor(user.id)
        magnitudeCircle.setColor(magnitudeColor)
        last_name.text = " "+ user.last_name
        if (!user.avatar.isNullOrEmpty()) {
            Picasso.get().load(Uri.parse(user.avatar)).networkPolicy(NetworkPolicy.OFFLINE).fit()
                    .centerCrop().into(avatar, object : Callback {
                        override fun onSuccess() {}

                        override fun onError(e: Exception) {
                            Picasso.get().load(Uri.parse(user.avatar))
                                    .fit().centerCrop().into(avatar)
                        }
                    })
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_view_item, parent, false)
            return UserViewHolder(view)
        }
    }

    private fun getMagnitudeColor(magnitude: Int): Int {
        val magnitudeColorResourceId: Int
        when (magnitude) {
            0, 1 -> magnitudeColorResourceId = R.color.magnitude1
            2 -> magnitudeColorResourceId = R.color.magnitude2
            3 -> magnitudeColorResourceId = R.color.magnitude3
            4 -> magnitudeColorResourceId = R.color.magnitude4
            5 -> magnitudeColorResourceId = R.color.magnitude5
            6 -> magnitudeColorResourceId = R.color.magnitude6
            7 -> magnitudeColorResourceId = R.color.magnitude7
            8 -> magnitudeColorResourceId = R.color.magnitude8
            9 -> magnitudeColorResourceId = R.color.magnitude9
            else -> magnitudeColorResourceId = R.color.magnitude10plus
        }
        return ContextCompat.getColor(itemView.context,magnitudeColorResourceId)
    }
}