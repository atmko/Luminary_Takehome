package cvdevelopers.takehome.view.clientlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cvdevelopers.githubstalker.databinding.FragmentClientListBinding
import cvdevelopers.takehome.view.common.BaseFragment
import cvdevelopers.takehome.viewmodel.ClientListViewModel
import cvdevelopers.takehome.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Fragment to display client list
 */
class ClientListFragment : BaseFragment() {

    private var _binding: FragmentClientListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var clientAdapter: ClientAdapter

    private lateinit var viewModel: ClientListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPresentationComponent().inject(this)

        configureValues()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentClientListBinding.inflate(inflater, container, false)

        configureViews()
        observeViewModel()

        return binding.root
    }

    private fun configureValues() {
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(ClientListViewModel::class.java)
        if (viewModel.clients.value!!.isEmpty()) {
            viewModel.getClients()
        }
    }

    private fun configureViews() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNewClients()
        }

        binding.recyclerViewClients.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = clientAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.clients.observe(viewLifecycleOwner, Observer { clients ->
            clientAdapter.updateClients(clients)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.swipeRefreshLayout.isRefreshing = it
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {
            binding.textViewErrorMessage.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}