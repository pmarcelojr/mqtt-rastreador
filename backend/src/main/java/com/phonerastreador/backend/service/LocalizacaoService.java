package com.phonerastreador.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.DispositivoRepository;
import com.phonerastreador.backend.repository.LocalizacaoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {

    private static final Logger log = LoggerFactory.getLogger(LocalizacaoService.class);

    @Autowired
    private OpenStreetMapService mapService;

    @Autowired
    private LocalizacaoRepository repository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Localizacao salvar(Localizacao localizacao) {
        Localizacao salvo = this.repository.save(localizacao);

        final String dispositivoId = localizacao.getDispositivo().getId();
        log.debug("Salvando localizacao para dispositivo ID: {}", dispositivoId);
        Optional<Dispositivo> encontrado = this.dispositivoRepository.findById(dispositivoId);
        if (encontrado.isPresent()) {
            Dispositivo dispositivo = encontrado.get();
            dispositivo.setUltimaLocalizacao(localizacao);
            dispositivo.setUltimaAtualizacao(LocalDateTime.now());
            this.dispositivoRepository.save(dispositivo);
        } else {
            log.warn("Nao encontrei dispositivo id '{}'", dispositivoId);
        }

        this.mapService.obterLocalizacao(localizacao);

        return salvo;
    }

    public List<Localizacao> getByUser(User usuario) {
        List<Localizacao> lista = this.repository.findByUsernameOrderByCriadoEmDesc(usuario);
        return lista;
    }

    public List<Localizacao> getByUser(User usuario, Integer quantidade) {
        PageRequest pagina = PageRequest.of(0, quantidade);
        return this.repository.findByUsernameOrderByCriadoEmDesc(usuario, pagina);
    }

    public Page<Localizacao> getDePara(User usuario, LocalDate de, LocalDate para, Pageable pagina) {
        LocalDateTime dataInicio = de.atTime(0, 0, 0);
        LocalDateTime dataFim = para.atTime(23, 59, 59);
        log.info("Buscando localizacoes entre {} e {}", dataInicio, dataFim);
        return this.repository.findByUsernameAndHorarioGpsBetween(usuario, dataInicio, dataFim, pagina);
    }    
}
