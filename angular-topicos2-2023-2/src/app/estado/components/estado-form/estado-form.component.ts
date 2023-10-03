import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Estado } from 'src/app/models/estado.model';
import { EstadoService } from 'src/app/services/estado.service';

@Component({
  selector: 'app-estado-form',
  templateUrl: './estado-form.component.html',
  styleUrls: ['./estado-form.component.css']
})
export class EstadoFormComponent {

  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private estadoService: EstadoService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

    const estado: Estado = this.activatedRoute.snapshot.data['estado'];
    this.formGroup = formBuilder.group({
      id:[(estado && estado.id)? estado.id : null],
      nome:[(estado && estado.nome)? estado.nome : '', Validators.required],
      sigla:[(estado && estado.sigla)? estado.sigla : '', Validators.required]
    })
  }

  salvar() {
    if (this.formGroup.valid) {
      const novoEstado = this.formGroup.value;
      if (novoEstado.id == null) {

        this.estadoService.save(novoEstado).subscribe({
          next: (estadoCadastrado) => {
            this.router.navigateByUrl('/estados/list');
          },
          error: (err) => {
            console.log('Erro ao salvar' + JSON.stringify(err));
          }
        })
      }
      else {

        this.estadoService.update(novoEstado).subscribe({
          next: (estadoCadastrado) => {
            this.router.navigateByUrl('/estados/list');
          },
          error: (err) => {
            console.log('Erro ao salvar' + JSON.stringify(err));
          }
        })
      }
    }
  }
}
