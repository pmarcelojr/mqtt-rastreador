import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { LocalizacaoService } from '../localizacao.service';
import { LocalizacaoDto } from '../interfaces/interfaces';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private map: any;

  private markers: L.Marker<any>[] = [];
  constructor(
    private localizacaoService: LocalizacaoService
  ) { }

  private initMap(): void {
    this.map = L.map('map', {
      center: [-13.4218236, -52.5652522],
      zoom: 4
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);
  }

  public from: any = '';
  public to: any = '';

  public addMarkerClick = () => {
    (this.map as L.Map).on('click', (e: any) => {
      const { lat, lng } = e.latlng;
      const coords = [lat, lng];
      this.addMarker(coords, '');
    });
  }

  private defaultIcon = L.icon({
    iconUrl: '/marker-icon.png',
    iconAnchor: [12, 40]
  });

  private addMarker(latLng: any, texto: string): void {
    const marker = L.marker(latLng, { icon: this.defaultIcon }).addTo(this.map);

    marker.bindPopup(texto);
    this.markers.push(marker);
  }

  public btnFiltrar() {
    const from = new Date(this.from);
    const to = new Date(this.to);
    console.log('deveria filtrar', from.getTime(), from.toLocaleString());
  }

  public btnResetar() {
    console.log('deveria resetar');
  }

  private getTipoConexao(item: LocalizacaoDto): string {
    switch (item.tipoConexao) {
      case 'WIFI':
        return `Wi-Fi`;
      default:
        return 'Desconhecido';
    }
  }

  private gerarTexto(item: LocalizacaoDto): string {
    return `
      <strong>${item.dispositivo.nome}</strong><br />
      Data: ${new Date(item.horarioGps).toLocaleString()}<br/>
      Bateria: ${item.bateria && item.bateria + '%' || '?'} (${item.bateriaStatus})<br />
      Endereço: <small>${item.endereco}</small><br />
      Tipo de conexão: ${this.getTipoConexao(item)}
      `;
  }

  private carregarMapas() {
    this.localizacaoService.getLocalizacoes().subscribe(
      (data: LocalizacaoDto[]) => {
        data.forEach(item => {
          const texto = this.gerarTexto(item);
          this.addMarker([item.latitude, item.longitude], texto);
        });
      },
      err => {
        console.error(err);
      }
    )
  }

  ngOnInit(): void {
    this.carregarMapas();
    this.initMap();
  }

}
