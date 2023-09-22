package com.eazyalgo.squidchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val Item_Receive = 1
    val Item_Sent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1)
        {
            //inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieved, parent, false)
            return ReceiveViewHolder(view)
        }
        else
        {
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }

    }

    override fun getItemCount(): Int {

        return messageList.size

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderID)){

            return Item_Sent
        }
        else
        {
            return Item_Receive
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){

            //do the stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        }
        else{
            //do stuff for receive view holder
            val viewHolder = holder as ReceiveViewHolder
            holder.receivedMessage.text = currentMessage.message
        }
    }
    class SentViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

        val sentMessage = itemview.findViewById<TextView>(R.id.txt_sentmessage)
    }
    class ReceiveViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val receivedMessage = itemview.findViewById<TextView>(R.id.txt_recievedmessage)

    }

}
