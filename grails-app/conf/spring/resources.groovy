import com.djamware.securepage.UserPasswordEncoderListener
import webhook.CustomUserDetailsService

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
    userDetailsService(CustomUserDetailsService)
}
