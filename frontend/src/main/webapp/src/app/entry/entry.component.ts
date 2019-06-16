import { EntryService } from './../_services/entry.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, AlertService } from '@app/_services';
import { Entry } from '@app/_models';

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit {

    entryForm: FormGroup;
    loading = false ;
    submitted = false ;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
      private entryService: EntryService,
        private alertService: AlertService
    ) {}

    ngOnInit() {
      this.entryForm = this.formBuilder.group({
            title: ['', Validators.required],
            description: ['', Validators.required],
          text: [ '', Validators.required ]
        });

    }

    // convenience getter for easy access to form fields
  get f () { return this.entryForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
      if (this.entryForm.invalid) {
            return;
        }

        this.loading = true;
      const entry: Entry = {
        title: this.f.title.value,
        description: this.f.description.value,
        text: this.f.text.value,
      };
      this.entryService.create(entry)
            .subscribe(
                data => {
                  console.log(data);
                this.alertService.success('Text Analysis : The entry contains total ' + data[ 'wordCount' ] + ' words and ' +
                  data[ 'charCount' ] + ' characters');
                this.loading = false;
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}

