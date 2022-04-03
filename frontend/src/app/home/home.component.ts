import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import * as L from 'leaflet';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  map: any;
  constructor(private userService: UserService) { }

  private initMap(): void {
    this.map = L.map('map', {
      center: [ -13.4218236,-52.5652522 ],
      zoom: 4
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    const marker = L.marker([ -23.557081, -46.498635 ]).addTo(this.map);
    marker.bindPopup("<b>Hello world!</b><br>I am a popup.");

    tiles.addTo(this.map);
  }

  ngOnInit(): void {
    this.initMap();
  }

}
