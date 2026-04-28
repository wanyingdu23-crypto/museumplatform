package com.cpt208.museumplatform.service;

import com.cpt208.museumplatform.entity.ArtifactEntity;
import com.cpt208.museumplatform.repository.ArtifactRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtifactSeedService {

    private final ArtifactRepository artifactRepository;

    public ArtifactSeedService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    @PostConstruct
    public void seedArtifacts() {
        if (artifactRepository.count() > 0) {
            return;
        }

        artifactRepository.saveAll(List.of(
            new ArtifactEntity(
                "Bronze Mirror",
                "Bronze Mirror",
                null,
                null,
                "A bronze mirror with patterned detail, preserved as a key artifact for reflective ritual culture.",
                "This bronze mirror was often associated with daily grooming, symbolism, and the social identity of its owner.",
                "A hidden ritual object reflects status and daily identity through its polished surface. What artifact is it?",
                "Bronze",
                "round, carved, reflective",
                "Chinese",
                "Asian Art",
                "seed",
                null,
                true
            ),
            new ArtifactEntity(
                "Celadon Bowl",
                "Celadon Bowl",
                null,
                null,
                "A celadon-glazed bowl with soft green tones and elegant ceramic craftsmanship.",
                "Its glaze and shape reflect refined ceramic production and aesthetic taste in historical daily life.",
                "A soft green ceramic vessel is known for elegant glaze and refined daily use. What artifact is it?",
                "Ceramic",
                "round, smooth, bowl",
                "Korean",
                "Asian Art",
                "seed",
                null,
                true
            ),
            new ArtifactEntity(
                "Gold Ornament",
                "Gold Ornament",
                null,
                null,
                "A small gold ornament that highlights precious-metal craftsmanship and decorative expression.",
                "The ornament may have functioned as a status symbol or ceremonial decoration in its original context.",
                "A precious metal object once served as a sign of status or ceremony in a small decorative form. What artifact is it?",
                "Metalwork",
                "small, carved, decorative",
                "Ancient",
                "Ancient Near Eastern Art",
                "seed",
                null,
                true
            ),
            new ArtifactEntity(
                "Painted Pottery Jar",
                "Painted Pottery Jar",
                null,
                null,
                "A painted pottery vessel with visible pattern language and storage-oriented form.",
                "Its painted surface suggests both practical use and cultural storytelling through visual motifs.",
                "A painted vessel combines use and visual storytelling through pattern on its surface. What artifact is it?",
                "Pottery",
                "jar, painted, curved",
                "Greek",
                "Greek and Roman Art",
                "seed",
                null,
                true
            ),
            new ArtifactEntity(
                "Jade Pendant",
                "Jade Pendant",
                null,
                null,
                "A jade pendant representing carved stone craftsmanship and symbolic ornamentation.",
                "The pendant likely carried social, ritual, or personal meaning while showcasing polished jade work.",
                "A carved stone ornament carried symbolic meaning while displaying polished craftsmanship. What artifact is it?",
                "Jade",
                "pendant, polished, carved",
                "Chinese",
                "Asian Art",
                "seed",
                null,
                true
            ),
            new ArtifactEntity(
                "Stone Tablet",
                "Stone Tablet",
                null,
                null,
                "A stone tablet used here to represent carved inscriptions and durable historical records.",
                "Stone tablets often preserve writing, commemoration, or official meaning through enduring material.",
                "A carved stone surface preserves memory and meaning through durable inscription. What artifact is it?",
                "Stone",
                "rectangular, carved, flat",
                "Ancient",
                "Egyptian Art",
                "seed",
                null,
                true
            )
        ));
    }
}
