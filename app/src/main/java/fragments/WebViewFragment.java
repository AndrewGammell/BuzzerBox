package fragments;

import abstracts.AbstractFragment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.buzzerbox.app.R;

public class WebViewFragment extends AbstractFragment {


    @Override
    protected int getLayout() {
        return R.layout.layout_webview;
    }

//    creates the view and loads the url.
    @Override
    protected void instantiateWidgets(View view) {
        WebView webView = (WebView) view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String url = "http://guyforceshiswifetodressinagarbagebagforthenextthreeyears.com/";
        webView.loadUrl(url);
    }


    // returns a new fragment
    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }
}
