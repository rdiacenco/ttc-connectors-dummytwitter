package org.activiti.cloud.connectors.twitter.controllers;

import org.activiti.cloud.connectors.twitter.model.Tweet;
import org.activiti.cloud.connectors.twitter.services.SocialFeedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyTwitterController {

    private SocialFeedService socialFeedService;

    public DummyTwitterController(SocialFeedService socialFeedService) {
        this.socialFeedService = socialFeedService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String home() {
        return "Hello From the Trending Topic Campaigns: Dummy Twitter Connector Service";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/feed/start")
    public void startFeed() {
        if (!socialFeedService.isStarted()) {
            socialFeedService.start();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/feed/stop")
    public void stopFeed() {
        if (socialFeedService.isStarted()) {
            socialFeedService.stop();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/feed")
    public boolean isStarted() {
        return socialFeedService.isStarted();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/feed/tweet")
    public void consumeTweet(@RequestBody Tweet t) {
        socialFeedService.consumeTweet(t);
    }
}

