import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getDados().subscribe(
      data => {
        this.content = JSON.stringify(data);
      },
      err => {
        console.log(err.error);
        this.content = 'Erro ao obter dados';
      }
    )
  }

}
