import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DispositivoService } from 'src/app/dispositivo.service';
import { DispositivoDto } from 'src/app/interfaces/interfaces';

@Component({
  selector: 'app-dispositivos',
  templateUrl: './dispositivos.component.html',
  styleUrls: ['./dispositivos.component.css']
})
export class DispositivosComponent implements OnInit {

  @ViewChild('btnFecharModal') btnFecharModal: ElementRef<HTMLDivElement> = {} as ElementRef;

  public dispositivos: DispositivoDto[] = [];
  public isCarregando = true;
  public exibirSenha = false;

  public formulario: FormGroup;

  constructor(private service: DispositivoService) {
    this.formulario = new FormGroup({
      nome: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      senha: new FormControl('', [
        Validators.required,
        Validators.maxLength(6),
        Validators.minLength(4)
      ]),
    });
  }

  ngOnInit(): void {
    this.carregarDispositivos();
  }

  public enviarFormulario() {
    if (!this.formulario.valid) {
      console.warn("Formulário Inválido");
      return;
    }

    this.service.criarDispositivo(this.formulario.value)
      .subscribe(
        (item: DispositivoDto) => {
          this.dispositivos.push(this.formatarDispositivoDto(item));
          this.btnFecharModal.nativeElement.click();
          this.formulario.reset();
        },
        err => {
          console.error(err);
        }
      );
  }

  public alternarExibirSenha(senha: HTMLInputElement) {
    this.exibirSenha = !this.exibirSenha;
    senha.type = this.exibirSenha ? 'text' : 'password';
  }

  private formatarDispositivoDto(item: DispositivoDto): DispositivoDto {
    const criadoEm = new Date(item.criadoEm);
    item.criadoEm = criadoEm.toLocaleString();
    if (item.ultimaAtualizacao == null) {
      item.ultimaAtualizacao = '-';
    }

    if (item.ultimaLocalizacao == null) {
      item.ultimaLocalizacao = '-';
    }
    return item;
  }

  private carregarDispositivos() {
    this.service.getDispositivos().subscribe(
      (data: DispositivoDto[]) => {
        this.dispositivos = data.map(this.formatarDispositivoDto);
      },
      err => {
        console.error(err);
      }
    ).add(() => {
      this.isCarregando = false;
    });
  }

}
