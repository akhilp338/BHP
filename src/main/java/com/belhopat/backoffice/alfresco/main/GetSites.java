package com.belhopat.backoffice.alfresco.main;

import java.io.IOException;

import com.belhopat.backoffice.alfresco.model.SiteEntry;
import com.belhopat.backoffice.alfresco.model.SiteList;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;

/**
 * Simple example that shows how to hit the Alfresco Public API with
 * the REST API to find the user's home network and to list up to
 * 10 sites visible to the user.
 *
 * @author jpotts
 */
public class GetSites extends BaseDeviceFns {

    public static void main(String[] args) {
        GetSites gse = new GetSites();
        gse.doExample();
    }

    public void doExample() {
        try {
            // Find the user's home network
            String homeNetwork = getHomeNetwork();

            // List some of the sites the user can see
            GenericUrl sitesUrl = new GenericUrl(getAlfrescoAPIUrl() +
                                                 homeNetwork +
                                                 SITES_URL + "?maxItems=10");
            HttpRequest request = getRequestFactory().buildGetRequest(sitesUrl);
            SiteList siteList = request.execute().parseAs(SiteList.class);
            System.out.println("Up to 10 sites you can see are:");
            for (SiteEntry siteEntry : siteList.list.entries) {
                    System.out.println(siteEntry.entry.id);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Done!");
    }
}
