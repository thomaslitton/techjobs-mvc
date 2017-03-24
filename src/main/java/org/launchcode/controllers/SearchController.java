package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    /**
     * This is called from the link in the fragments.html:
     * `<a th:href="@{/search}">Search</a>`
     *
     * as mentioned below, `search` is defined above by the class,
     * which means `/search` goes to here.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);

        // I'm returning the same thing as above.
        // the value here gets `.html` added to it to
        // find the html page to use.  So `search` becomes
        // `search.html` and therefor the `search.html`
        // file gets used.
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    /**
     * I know this should be `results` because of the path used in the
     * form in the search.html page:
     *
     * `<form th:action="@{/search/results}">`
     *
     * `search` part is defined above, so we only need the last part here
     *
     *  Feel free to experiment by changing the form action in the search.html
     *  page and the value here.  See what works and what doesn't.
     */
    @RequestMapping(value = "results")
    public String searchResults(Model model) {

        System.out.println("I'm in search results!!!");

        // I'm returning the same thing as above.
        // the value here gets `.html` added to it to
        // find the html page to use.  So `search` becomes
        // `search.html` and therefor the `search.html`
        // file gets used.
        return "search";
    }
}
