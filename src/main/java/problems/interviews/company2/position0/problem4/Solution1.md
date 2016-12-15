DNS is used to resolve a human friendly name (domain name) such as `http://www.google.com` to a machine friendly name (ipv4 or ipv6) such as `216.58.195.78`.
A DNS server is used to resolve queries such as these.
A DNS server stores mappings of domain names to values, known as records.
These records can map to ip addresses, other domains, or other information.
If a DNS server cannot resolve an address it might reach out to a “parent” server to resolve the query and most likely  cache that result to speed up future local lookup.