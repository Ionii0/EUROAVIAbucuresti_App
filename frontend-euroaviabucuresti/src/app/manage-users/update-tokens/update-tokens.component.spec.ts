import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTokensComponent } from './update-tokens.component';

describe('UpdateTokensComponent', () => {
  let component: UpdateTokensComponent;
  let fixture: ComponentFixture<UpdateTokensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateTokensComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateTokensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
