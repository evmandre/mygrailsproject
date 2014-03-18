import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.DefaultFacebookClient;
import com.restfb.types.User;


class FacebookController {
   def facebookKeys
 
   def index = {}
 
   def authent = {
	  def redirectURI = createLink(absolute:true, action: 'callback')
	  redirect(url: "https://www.facebook.com/dialog/oauth?client_id=${facebookKeys.clientCode}&amp;redirect_uri=${redirectURI}")
   }
 
   def callback = {
	  // Request an access token by fetching url with given code.
	  def redirectURI = createLink(absolute:true, action: 'callback')
	  def rootUrl = "https://graph.facebook.com/oauth/access_token?client_id=${facebookKeys.clientCode}&amp;client_secret=${facebookKeys.clientSecret}"
	  String accessTokenURL = rootUrl + "&amp;redirect_uri=${redirectURI}&amp;code=${params.code.encodeAsURL()}"
	  String result = new URL(accessTokenURL).getText()
	  // Access token is first key=value pairs value.
	  String accessToken = result.tokenize("&amp;")[0].tokenize("=")[1]
	  // Use a facebook client to request current logged in user friends.
	  FacebookClient fb = new DefaultFacebookClient(accessToken)
	  Connection friends = fb.fetchConnection("me/friends", User.class)
	  render(view: 'callback', model: [friends: friends.data])
   }
}
