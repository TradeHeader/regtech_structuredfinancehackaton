package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.Clause;
import cdm.legaldocumentation.master.Clause.ClauseBuilder;
import cdm.legaldocumentation.master.Clause.ClauseBuilderImpl;
import cdm.legaldocumentation.master.Clause.ClauseImpl;
import cdm.legaldocumentation.master.meta.ClauseMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A type for documenting additional clause that cannot yet be represented with the model and yet needed for a digital representation of the agreement
 * @version ${project.version}
 */
@RosettaDataType(value="Clause", builder=Clause.ClauseBuilderImpl.class, version="${project.version}")
public interface Clause extends RosettaModelObject {

	ClauseMeta metaData = new ClauseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The  name or identifier associated to this clause 
	 */
	String getIdentifier();
	/**
	 * Content of this bespoke clause
	 */
	String getTerms();
	/**
	 * Additional hierarchichal components of the clause if relevant
	 */
	List<? extends Clause> getSubcomponents();

	/*********************** Build Methods  ***********************/
	Clause build();
	
	Clause.ClauseBuilder toBuilder();
	
	static Clause.ClauseBuilder builder() {
		return new Clause.ClauseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Clause> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Clause> getType() {
		return Clause.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("identifier"), String.class, getIdentifier(), this);
		processor.processBasic(path.newSubPath("terms"), String.class, getTerms(), this);
		processRosetta(path.newSubPath("subcomponents"), processor, Clause.class, getSubcomponents());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ClauseBuilder extends Clause, RosettaModelObjectBuilder {
		Clause.ClauseBuilder getOrCreateSubcomponents(int _index);
		List<? extends Clause.ClauseBuilder> getSubcomponents();
		Clause.ClauseBuilder setIdentifier(String identifier);
		Clause.ClauseBuilder setTerms(String terms);
		Clause.ClauseBuilder addSubcomponents(Clause subcomponents0);
		Clause.ClauseBuilder addSubcomponents(Clause subcomponents1, int _idx);
		Clause.ClauseBuilder addSubcomponents(List<? extends Clause> subcomponents2);
		Clause.ClauseBuilder setSubcomponents(List<? extends Clause> subcomponents3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("identifier"), String.class, getIdentifier(), this);
			processor.processBasic(path.newSubPath("terms"), String.class, getTerms(), this);
			processRosetta(path.newSubPath("subcomponents"), processor, Clause.ClauseBuilder.class, getSubcomponents());
		}
		

		Clause.ClauseBuilder prune();
	}

	/*********************** Immutable Implementation of Clause  ***********************/
	class ClauseImpl implements Clause {
		private final String identifier;
		private final String terms;
		private final List<? extends Clause> subcomponents;
		
		protected ClauseImpl(Clause.ClauseBuilder builder) {
			this.identifier = builder.getIdentifier();
			this.terms = builder.getTerms();
			this.subcomponents = ofNullable(builder.getSubcomponents()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("identifier")
		public String getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("terms")
		public String getTerms() {
			return terms;
		}
		
		@Override
		@RosettaAttribute("subcomponents")
		public List<? extends Clause> getSubcomponents() {
			return subcomponents;
		}
		
		@Override
		public Clause build() {
			return this;
		}
		
		@Override
		public Clause.ClauseBuilder toBuilder() {
			Clause.ClauseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Clause.ClauseBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getTerms()).ifPresent(builder::setTerms);
			ofNullable(getSubcomponents()).ifPresent(builder::setSubcomponents);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Clause _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(terms, _that.getTerms())) return false;
			if (!ListEquals.listEquals(subcomponents, _that.getSubcomponents())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (terms != null ? terms.hashCode() : 0);
			_result = 31 * _result + (subcomponents != null ? subcomponents.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Clause {" +
				"identifier=" + this.identifier + ", " +
				"terms=" + this.terms + ", " +
				"subcomponents=" + this.subcomponents +
			'}';
		}
	}

	/*********************** Builder Implementation of Clause  ***********************/
	class ClauseBuilderImpl implements Clause.ClauseBuilder {
	
		protected String identifier;
		protected String terms;
		protected List<Clause.ClauseBuilder> subcomponents = new ArrayList<>();
	
		public ClauseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("identifier")
		public String getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("terms")
		public String getTerms() {
			return terms;
		}
		
		@Override
		@RosettaAttribute("subcomponents")
		public List<? extends Clause.ClauseBuilder> getSubcomponents() {
			return subcomponents;
		}
		
		public Clause.ClauseBuilder getOrCreateSubcomponents(int _index) {
		
			if (subcomponents==null) {
				this.subcomponents = new ArrayList<>();
			}
			Clause.ClauseBuilder result;
			return getIndex(subcomponents, _index, () -> {
						Clause.ClauseBuilder newSubcomponents = Clause.builder();
						return newSubcomponents;
					});
		}
		
	
		@Override
		@RosettaAttribute("identifier")
		public Clause.ClauseBuilder setIdentifier(String identifier) {
			this.identifier = identifier==null?null:identifier;
			return this;
		}
		@Override
		@RosettaAttribute("terms")
		public Clause.ClauseBuilder setTerms(String terms) {
			this.terms = terms==null?null:terms;
			return this;
		}
		@Override
		public Clause.ClauseBuilder addSubcomponents(Clause subcomponents) {
			if (subcomponents!=null) this.subcomponents.add(subcomponents.toBuilder());
			return this;
		}
		
		@Override
		public Clause.ClauseBuilder addSubcomponents(Clause subcomponents, int _idx) {
			getIndex(this.subcomponents, _idx, () -> subcomponents.toBuilder());
			return this;
		}
		@Override 
		public Clause.ClauseBuilder addSubcomponents(List<? extends Clause> subcomponentss) {
			if (subcomponentss != null) {
				for (Clause toAdd : subcomponentss) {
					this.subcomponents.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("subcomponents")
		public Clause.ClauseBuilder setSubcomponents(List<? extends Clause> subcomponentss) {
			if (subcomponentss == null)  {
				this.subcomponents = new ArrayList<>();
			}
			else {
				this.subcomponents = subcomponentss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public Clause build() {
			return new Clause.ClauseImpl(this);
		}
		
		@Override
		public Clause.ClauseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Clause.ClauseBuilder prune() {
			subcomponents = subcomponents.stream().filter(b->b!=null).<Clause.ClauseBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null) return true;
			if (getTerms()!=null) return true;
			if (getSubcomponents()!=null && getSubcomponents().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Clause.ClauseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Clause.ClauseBuilder o = (Clause.ClauseBuilder) other;
			
			merger.mergeRosetta(getSubcomponents(), o.getSubcomponents(), this::getOrCreateSubcomponents);
			
			merger.mergeBasic(getIdentifier(), o.getIdentifier(), this::setIdentifier);
			merger.mergeBasic(getTerms(), o.getTerms(), this::setTerms);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Clause _that = getType().cast(o);
		
			if (!Objects.equals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(terms, _that.getTerms())) return false;
			if (!ListEquals.listEquals(subcomponents, _that.getSubcomponents())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (terms != null ? terms.hashCode() : 0);
			_result = 31 * _result + (subcomponents != null ? subcomponents.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ClauseBuilder {" +
				"identifier=" + this.identifier + ", " +
				"terms=" + this.terms + ", " +
				"subcomponents=" + this.subcomponents +
			'}';
		}
	}
}
