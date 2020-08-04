package cvdevelopers.takehome.view.clientlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cvdevelopers.githubstalker.databinding.ItemClientBinding
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.utils.image.ImageLoader

class ClientAdapter(private val imageLoader: ImageLoader) :
        RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    private val clients = arrayListOf<Client>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
                ItemClientBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                )
        )
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    inner class ClientViewHolder(var binding: ItemClientBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client: Client = clients[position]

        holder.binding.textViewFullName.text = client.name.getFullNme()
        imageLoader.loadCircularImage(
                client.picture.medium, holder.binding.clientImageView
        )
    }

    fun updateClients(updatedClients: List<Client>) {
        clients.clear()
        clients.addAll(updatedClients)
        notifyDataSetChanged()
    }
}