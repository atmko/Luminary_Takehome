package cvdevelopers.takehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.dagger.presentation.PresentationModule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as LuminaryTakeHomeApplication)
                .appComponent
                .newPresentationComponent(PresentationModule())
                .inject(this)

    }

}
