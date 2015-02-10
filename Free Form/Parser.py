# Created by Brian Clement Tracy
import urllib2
import re

class Constants:
    SRC_URL = 'http://www.briantracy.xyz/freeform'


def formatted_html_from_page(page):
    """ Returns the HTML properly formatted """
    response = urllib2.urlopen(page)
    return response.read()


def stripped_html_from_source(formatted_html):
    """ Returns the stripped version of an html doc. Max one whitespace - no newlines """
    return " ".join(formatted_html.split())

def list_of_tags(tag, html):
    """ Returns an array of the contents of the specified HTML element """
    fulltag = r'<%s.*?>.*?</%s>' % (tag, tag)
    return re.findall(fulltag, html)

def contents_of_tag(tag):
    """ Get the actual text contained in a single tag"""
    tagtext = r'>.*?<'
    print tag
    return re.match(tagtext, tag)


if __name__ == '__main__':
    html = formatted_html_from_page(Constants.SRC_URL)
    stripped = stripped_html_from_source(html)

    print stripped

    tag = 'table'

    tags = list_of_tags(tag, stripped)

    print type(tags)
    print tags





