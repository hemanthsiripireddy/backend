package com.siripireddy.azureadpractice.controller;

import com.siripireddy.azureadpractice.entity.Document;
import com.siripireddy.azureadpractice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {



    @Autowired
    private DocumentService documentService;

    @PreAuthorize("hasPermission(#id, 'com.example.Document', 'READ')")
    @GetMapping("/{id}")
    public Document getDocument(@PathVariable Long id) {
        return documentService.findById(id);
    }

    @PreAuthorize("hasPermission(#id, 'com.example.Document', 'WRITE')")
    @PutMapping("/{id}")
    public void updateDocument(@PathVariable Long id, @RequestBody String newContent) {
        Document document = documentService.findById(id);
        documentService.updateDocument(document, newContent);
    }
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }
    @GetMapping("/test")
    public String test() {
        return "test api";
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
//        return documentService.getDocumentById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping("/post")
    public Document createDocument(@RequestBody Document document) {
        return documentService.saveDocument(document);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document document) {
//        return documentService.getDocumentById(id)
//                .map(existingDocument -> {
//                    existingDocument.setTitle(document.getTitle());
//                    existingDocument.setContent(document.getContent());
//                    return ResponseEntity.ok(documentService.saveDocument(existingDocument));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
//        return documentService.getDocumentById(id)
//                .map(document -> {
//                    documentService.deleteDocument(id);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}

