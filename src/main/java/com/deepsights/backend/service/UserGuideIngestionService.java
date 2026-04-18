package com.deepsights.backend.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

//    Step 1: Implement CommandLineRunner to execute the ingestion process automatically on application startup.
@Service // "@Service" Required for Spring to detect and run the CommandLineRunner
public class UserGuideIngestionService  implements CommandLineRunner {

//    Step 2: Inject the VectorStore instance that will generate and hold the document embeddings.
    private final VectorStore vectorStore;

    UserGuideIngestionService(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

//    Step 3: Load the raw user manual text file directly from the classpath resources.
    @Value("classpath:documents/user_manual.txt")
    private Resource userManualResource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started Injecting Vector Store");

//      Step 4: Initialize a TextReader to extract the raw text content from the resource file.
        TextReader textReader =  new TextReader(userManualResource);

//      Step 5:Configure a TokenTextSplitter to break the large document into smaller, manageable chunks for optimal LLM context retrieval.
        TokenTextSplitter textSplitter = TokenTextSplitter.builder()
                .withChunkSize(750)
                .withMinChunkSizeChars(350)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(10000)
                .withKeepSeparator(true)
                .build();

//      Step 6: Extract the text and apply the splitting strategy to generate a list of chunked Document objects.
        List<Document> documents = textSplitter.apply(textReader.get());

//      Step 7: Add the chunked documents to the Vector Store (this automatically generates and stores their embeddings).
        vectorStore.add(documents);

        System.out.println("Finished Injecting Vector Store");
    }
}
