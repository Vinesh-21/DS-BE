package com.deepsights.backend.tools;

import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

// Step 1: Register this class as a Spring Component so it can be automatically detected and injected.
@Component
public class UserGuideTool {

    // Step 2: Inject the VectorStore we configured to enable searching.
    private final VectorStore vectorStore;

    public UserGuideTool(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Tool(
            name = "search_user_guide",
            description = """
        Search the portal user guide for step-by-step instructions.
        
        Use this tool ONLY when the user asks HOW TO DO something in the portal.
        Examples: "how do I add a site", "how to create a gateway", "steps to add a load",
                  "guide me to configure a meter", "where do I find load readings".
        
        - Pass the user's question directly as the query.
        - Returns structured guide steps as a concatenated string.
        - Do NOT call this for greetings, data queries, or flow triggers.
        - Do NOT call this for "what can you do" or capability questions.
        """
    )
    public String searchUserGuide(String query){
        // Step 4: Perform a similarity search against the Vector Store using the user's query.
        // We use the builder to specify we only want the top 3 most relevant chunks.
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(5)
                        .build()
        );

        // Step 5: Handle the edge case where the vector store finds no matching instructions.
        if (results.isEmpty()) {
            return "No relevant guide steps found for this query.";
        }

        // Step 6: Extract the text from the Document objects and combine them into a single String.
        // This String is what gets returned to the LLM so it can read the instructions.
        return results.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n---\n\n"));
    }
}
