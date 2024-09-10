package com.siripireddy.azureadpractice.service;

import com.siripireddy.azureadpractice.entity.Document;
import com.siripireddy.azureadpractice.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;
    public Document findById(Long id) {
        return documentRepo.findById(id).orElse(null);
    }

    @PreAuthorize("hasPermission(#document, 'WRITE')")
    public void updateDocument(Document document, String newContent) {
        document.setContent(newContent);
        documentRepo.save(document);
    }

    public List<Document> getAllDocuments() {
        return null;
    }

    public Document getDocumentById(Long id) {
        return null;
    }

    public Document saveDocument(Document document) {
        return documentRepo.save(document);
    }

    public void deleteDocument(Long id) {
    }
}
