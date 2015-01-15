# Created by Brian Clement Tracy
import urllib2
import re


def formatted_html_from_page(page):
    response = urllib2.urlopen(page)
    return response.read()


def stripped_html_from_source(formatted_html):
    return " ".join(formatted_html.split())


if __name__ == '__main__':
    html = formatted_html_from_page('http://www.briantracy.xyz/resume')
    stripped = stripped_html_from_source(html)
    print stripped

