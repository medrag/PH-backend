package fr.pizzahut.pizzahutrhms.controllers;

import fr.pizzahut.pizzahutrhms.models.dto.PieceJointe;
import fr.pizzahut.pizzahutrhms.services.PieceJointeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pieces-jointes")
@Slf4j
public class PieceJointeController {

    private final PieceJointeService pieceJointeService;

    @Autowired
    public PieceJointeController(PieceJointeService pieceJointeService) {
        this.pieceJointeService = pieceJointeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadPj(@PathVariable Long id) throws Exception {
        try {
            PieceJointe pjToDownload = this.pieceJointeService.downloadPj(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(pjToDownload.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + pjToDownload.getName() + "\"")
                    .body(new ByteArrayResource(pjToDownload.getContent()));
        } catch (Exception e) {
            String errorMsg = "Erreur lors du téléchargement du fichier";
            log.error(errorMsg);
            throw new Exception(errorMsg);
        }
    }
}
