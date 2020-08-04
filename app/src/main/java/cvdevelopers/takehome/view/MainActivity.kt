package cvdevelopers.takehome.view

import android.os.Bundle
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.view.clientlist.ClientListFragment
import cvdevelopers.takehome.view.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPresentationComponent().inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_fragment_container, ClientListFragment())
                    .commit()
        }
    }
}
