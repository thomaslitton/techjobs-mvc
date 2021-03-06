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
     *
     *  Request Parameters:
     *  The @RequestParam means that value is coming from the http request
     *  as an http parameter.  These are the parameters that get passed from
     *  the html form in search.html.
     *
     *  The names of the arguments (searchType and searchTerm) must match
     *  the name of the parameters in the html form:
     * <input
     * type="radio"
     * name="searchType"
     * th:id="${column.key}"
     * th:value="${column.key}"
     * th:checked="${column.key == 'all'}"/>
     *
     * AND
     *
     * <input type="text" name="searchTerm" id="searchTerm" />
     */
    @RequestMapping(value = "results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        // This is just a copy of the line above.  How did i know
        // this line was needed?  It was a guess!  Right now these
        // 2 methods do the same thing (display the search.html page),
        // so they should have the same implementation.
        // If you want a more technical answer:
        // The thing not displaying is the columns under the line:
        // `<span th:each="column : ${columns}">`
        // The ${columns} is connected to the `"columns"` here, so
        // the value referenced by ${columns} is set here to
        // `ListController.columnChoices`
        // Feel free to experiment more here:
        // * Try to change `"columns"` here and ${columns} in search.html
        //   to another name "col" and ${col} for example.
        // * Try changing the value set here to something other then
        //   `ListController.columnChoices`  try:
        //    HashMap otherCols = new HashMap();
        //    otherCols.put("newCol", "other col");
        //    model.addAttribute("columns", otherCols);
        // * Try setting other attributes with:
        //    model.addAttribute("otherAttr", "other attribute");
        //    AND use it in the template:
        //    `<p>Some other attribute <span th:text="${otherAttr}" /></p>`
        model.addAttribute("columns", ListController.columnChoices);

        // Now we get the data.  That method has already been written so
        // we just need to call it.
        ArrayList<HashMap<String, String>> searchResults = JobData.findByColumnAndValue(searchType,searchTerm);
        // Print it out so we know we are getting results.
        System.out.println("Search results:" + searchResults);

        // Set it as an attribute, just like the columns above.
        // This is what we will use in the search.html page.
        // Just like ${columns} references the columns set above
        // ${searchResults} will reference the searchResults set here.
        model.addAttribute("searchResults", searchResults);

        // I'm returning the same thing as above.
        // the value here gets `.html` added to it to
        // find the html page to use.  So `search` becomes
        // `search.html` and therefor the `search.html`
        // file gets used.
        return "search";
    }
}
