import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cidade } from 'src/app/models/cidade.model';
import { Estado } from 'src/app/models/estado.model';
import { CidadeService } from 'src/app/services/cidade.service';
import { EstadoService } from 'src/app/services/estado.service';

@Component({
  selector: 'app-cidade-form',
  templateUrl: './cidade-form.component.html',
  styleUrls: ['./cidade-form.component.css']
})
export class CidadeFormComponent {

  formGroup: FormGroup;
  estados: Estado[] = [];

  constructor(private formBuilder: FormBuilder,
    private cidadeService: CidadeService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private estadoService: EstadoService) {

    const cidade: Cidade = this.activatedRoute.snapshot.data['cidade'];
    this.formGroup = formBuilder.group({
    id:[(cidade && cidade.id)? cidade.id : null],
    nome:[(cidade && cidade.nome)? cidade.nome : '', Validators.required],
    estado:[(cidade && cidade.estado)? cidade.estado.id : '', Validators.required]
    })
  }

  ngOnInit(): void {
    this.estadoService.findAll().subscribe(data => {
      this.estados = data;
    });
  }

  salvar() {
    if (this.formGroup.valid) {
      const novaCidade = this.formGroup.value;
      if (novaCidade.id == null) {

        this.cidadeService.save(novaCidade).subscribe({
          next: (cidadeCadastrada) => {
            this.router.navigateByUrl('/cidades/list');
          },
          error: (err) => {
            console.log('Erro ao salvar' + JSON.stringify(err));
          }
        })
      }
      else {

        this.cidadeService.update(novaCidade).subscribe({
          next: (cidadeCadastrada) => {
            this.router.navigateByUrl('/cidades/list');
          },
          error: (err) => {
            console.log('Erro ao salvar' + JSON.stringify(err));
          }
        })
      }
    }
  }

  excluir() {

    const novaCidade = this.formGroup.value;
      if (novaCidade.id == null) {

        this.cidadeService.delete(novaCidade).subscribe({
          next: (cidadeCadastrada) => {
            this.router.navigateByUrl('/cidades/list');
          },
          error: (err) => {
            console.log('Erro ao excluir' + JSON.stringify(err));
          }
        })
      }
  }
}
