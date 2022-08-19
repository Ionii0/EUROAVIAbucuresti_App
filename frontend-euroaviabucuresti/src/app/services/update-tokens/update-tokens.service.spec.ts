import { TestBed } from '@angular/core/testing';

import { UpdateTokensService } from './update-tokens.service';

describe('UpdateTokensService', () => {
  let service: UpdateTokensService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdateTokensService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
