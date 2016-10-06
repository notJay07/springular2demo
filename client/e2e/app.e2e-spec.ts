import { BooooksuiPage } from './app.po';

describe('booooksui App', function() {
  let page: BooooksuiPage;

  beforeEach(() => {
    page = new BooooksuiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
