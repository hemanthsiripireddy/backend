package com.siripireddy.azureadpractice.config;

import com.siripireddy.azureadpractice.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;

public class PermissionEvaluator {

    @Autowired
    private AclService aclService;

    @Autowired
    private SidRetrievalStrategy sidRetrievalStrategy;

    @Autowired
    private MutableAclService mutableAclService;

    public void assignPermissions(Document document, String username, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(Document.class, document.getId());
        Sid sid = new PrincipalSid(username);

        MutableAcl acl;
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            acl = mutableAclService.createAcl(oid);
        }

        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        mutableAclService.updateAcl(acl);
    }
}
