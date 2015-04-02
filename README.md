# SPLiCE: Software-Product Lines in HealthCarE

## Motivation

The last couple of years have seen nevertheless a significant growth in criticism of HIS effectiveness in supporting more efficient healthcare services. 
It is generally understood that the inhibitor factors for physicians, clinics, or hospitals to adopt HIS fall into three main clusters, viz., organizational, environmental, and technological. 
The technological inhibitor factors can be summarized into:

- high development cost;
- lack of open-standardization;
- technology-orientation; and
- troublesome maintainability.

Recently, attention has been drawn to the potential of Model-Driven Engineering (MDE) in general, and OMG's MDA approach in particular, for overcoming inhibitor factors to the deployment of maintainable HIS. 
MDA allows for:

- low development cost and (hence) reducing the backlog of potential applications;
- enabling standards and interoperability;
- platform-independence and lessened vendor lock-in; and
- abstraction to deal with complexity and maintenance.

In this project, we propose a method that builds upon the potential that MDE - not the more particular MDA - has to offer to HIS. 
In fact, our MDE method shall provide additional evidence for some of the benefits just elicited above. 
However, in our vision, there are two main challenges yet that should be addressed for model-driven HIS to be able to stand for longer life spans:

- Inherent complexity of clinical data modeling. This is an issue—specially if the clinical data model is to be built from scratch—either when developing a single HIS, or a family of HIS as is often the case for MDE. It requires too much attention from the development team, being it only one piece of the HIS development effort.
- Ad-hoc architectural modeling. An effective use of architectural design techniques is specially required in the domain of HIS. Typically, families of HIS (e.g., prehospital emergency, epidemiological surveillance, intrahospital patient monitoring) vary within styles of system communication and control. We argue that these styles deserve research attention in order for us to develop HIS for maintainability and reuse.

Altogether, these two challenges have motivated us in the design of an MDE method that seeks a higher degree of maintainability and reuse by adhering to three main principles: transversalization of data models and architecture models, dualization of information and clinical data models, and stylization of architecture models. 
To support the envisioned MDE method on the generation of HIS skeletons, we are progressing work on the development of a software workbench fully-based on open artifacts. 
This software workbench, which is called SPLiCE (http://dl.acm.org/citation.cfm?doid=2110363.2110447), allows weaving together clinical data models described according to the openEHR specifications (http://www.openehr.org) and architecture models specified in the Acme language (http://www.cs.cmu.edu/~acme/).

We have been working out the proposed method primarily in two families of HIS: prehospital emergency healthcare and epidemiological surveillance. 
The code provided herein offers support for generating HIS skeletons for emergency healthcare systems, following the architecture style proposed in our previous work on the AToMS system (http://dx.doi.org/10.1155/2011/560209).

## Pre-requisites

Currently, the SPLiCE system is offered as a set of standalone components and models that must be "weaved together" manually to generate the intended HIS prototypes. 
Nevertheless, these components and models are all provided within a single integrated development environment (IDE) provided by the Eclipse platform (https://eclipse.org/downloads/packages/eclipse-modeling-tools/), which may alleviate the inconvenients of manual user intervention. 
The needed Eclipse plugins that allow SPLiCE components and models to work are the following:

1.	Java Emitter Template (JET) SDK (Modeling Component)
2.	ATL (Modeling Component)
3.	Eclipse XSL Developer Tools (Software)
4.	EGit (Marketplace). This component is needed for importing the SPLiCE components and models from this repository

Enjoy!

## Acknowledgements

This work was partially supported by the Brazilian Funding Agencies FAPERJ, CNPq, and by the Brazilian Ministry of Science, Technology and Innovation (MCTI). 
The authors are with the National Laboratory for Scientific Computing (LNCC - http://www.lncc.br). 
A. T. A. Gomes and A. Ziviani, the key architects of the SPLiCE system, are also with the National Institute of Science and Technology for Medicine Assisted by Scientific Computing (INCT-MACC - http://macc.lncc.br).
