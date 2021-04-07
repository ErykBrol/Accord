package org.accord.platform.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SuggestionsService {
	private Map<String, HashSet<String>> topicSuggestions = new HashMap<>();

	public void addSuggestion(String topic, String submission) {
		StringBuilder suggestion = new StringBuilder(submission.substring(0, 1).toUpperCase());
		if (submission.length() > 1) {
			suggestion.append(submission.substring(1));
		}

		getSuggestions(topic.toUpperCase()).add(suggestion.toString());
	}

	public HashSet<String> getSuggestions(String topic) {
		String TOPIC = topic.toUpperCase();
		if (topicSuggestions.get(TOPIC) == null) {
			topicSuggestions.put(TOPIC, new HashSet<>());
		}
		return topicSuggestions.get(TOPIC);
	}
}
