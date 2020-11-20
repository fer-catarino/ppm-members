package com.init.members.rest;

import java.util.List;
import java.util.Optional;
import com.init.members.dao.MembersDAO;
import com.init.members.entitys.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
public class MembersREST {

    @Autowired
    private MembersDAO membersDAO;

    @GetMapping
    public ResponseEntity<List<Members>> getMembers(){
        List<Members> members = membersDAO.findAll();
        return ResponseEntity.ok(members);
    }

    @RequestMapping(value="{membersId}")// /members/{membersId} -> /members/1
    public ResponseEntity<Members> getMembersById(@PathVariable("membersId") Long membersId){
        Optional<Members> optionalMembers= membersDAO.findById(membersId);
        if(optionalMembers.isPresent()){
            return ResponseEntity.ok(optionalMembers.get());
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    //Insertar a la base de datos
    @PostMapping // /members (POST)
    public ResponseEntity<Members> createMembers(@RequestBody Members members){
        Members newMembers = membersDAO.save(members);
        return ResponseEntity.ok(newMembers);
    }

    @DeleteMapping(value="{membersId}") // /members(DELETE)
    public ResponseEntity<Void> deleteMembers(@PathVariable("membersId") Long membersId){
        membersDAO.deleteById(membersId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Members> updateTechnologies(@RequestBody Members members){
        Optional<Members> optionalMembers= membersDAO.findById(members.getId());
        if(optionalMembers.isPresent()) {
            Members updateMembers = optionalMembers.get();
            updateMembers.setName(members.getName());
            membersDAO.save(updateMembers);
            return ResponseEntity.ok(updateMembers);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value="hello", method=RequestMethod.GET)
    public String hello() {
        return "Hello Word";
    }

}