package com.mitrran.msulearningapi.utils;

import lombok.Getter;

@Getter
public class ChatBotContextProvider {

    private String forums;

    public String getMsu() {
        return """
                Management and Science University (MSU) curates its academia and co-curriculum on the foundation of preparing its students for the relevant meta and soft skills acquisition. This gives them a head start among their peers, well positioning themselves for selection by potential employers due to the compelling skill sets and learning experiences they possess. Its graduate’s employability rating of ninety-seven point five percent (97.5%) secures a job for a graduate within the first six months of his or her graduation.
                An enriching learning experience that offers a variety of global mobility activities together with the nurturing of an entrepreneurial mindset and cultural competency further defines the university’s vibrant ecosystem. The Global Mobility Programme (GMP), Global Leadership Programme (GLP), and Global Internship Programme open doors for global exposure and better academic and non-academic experiences among students.
                In 2019, MSU became Asia’s first to be recognised by Netherlands-based ACEEU (Accreditation Council for Entrepreneurial & Engaged Universities) as Entrepreneurial and Engaged.
                MSU has also been awarded Platinum 5 Crowns by the UK’s Accreditation Service for International Schools, Colleges and Universities (ASIC) and QS-rated 5-Stars on the metrics of teaching, graduate employability, facilities, social responsibility, and inclusiveness.
                Currently ranked as a QS World Top 100 among the world’s Top 50 Universities Under 50, ranked 147th among Asia’s Best Universities, and Times Higher Education (THE) Top 601st for Impact Ranking. The university is among the top 47% of the world’s best universities in QS WUR 2021, as well as the top 200 for impact on quality education.""";
    }

    public String getMsu_vision() {
        return """
                Vision & Mission
                VISION
                MSU aspires to be a leading transformative global university, engaging the society through entrepreneurship, research, innovation, industry  embedment, and holistic education for a better future.
                MISSION
                TRANSFORMATIVE
                To provide compelling transformative learning experiences through educational opportunities and a collaborative learning environment, both centred on pioneering
                socio-economic transformations through innovative deliveries and with ethical values.
                GLOBAL
                To be a leading globally connected university rooted in culture yet diverse in inclusiveness, providing pathways to knowledge and facilitating knowledge exchange through mobility.
                ENTREPRENEURIAL
                To be the catalyst of a sustainable entrepreneur ecosystem promoting entrepreneurial culture in building leaders with a global mindset.
                RESEARCH & INNOVATION DRIVEN
                To promote a culture of interdisciplinary as well as translational research and innovation in
                responding to national and global challenges, and facilitate commercialisation of the outcomes through entrepreneurship and incubation.
                INDUSTRY EMBEDDED
                To enhance an already collaborative learning environment with the embedment of industries, where applications of knowledge and research align with contemporary needs and are delivered through a curriculum that complements career pathways with the advantages of global mobility and community engagement.
                CARING INSTITUTION
                To ensure the advancement in life of the talented but disadvantaged is not impeded by
                socio-economic standing, with opportunities that enable the gifted to achieve their true potential and actualise their dreams.
                HOLISTIC EDUCATION
                To mould holistic graduates through a balanced education that enriches their personal, interpersonal and social skills, with a multi-disciplinary approach that nurtures leadership, integrity, professionalism, academic excellence, and a passion for lifelong learning.
                BETTER FUTURE
                To create diversified career pathways that help transform lives into responsible global citizenship contributing professional talent towards a better future.""";
    }

    public void setForums(String forum) {
        forums = getForums() + forum;
    }


    public String getChatContext() {
        return getMsu();
    }
}
