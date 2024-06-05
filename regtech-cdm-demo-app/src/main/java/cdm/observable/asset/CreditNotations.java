package cdm.observable.asset;

import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditNotations;
import cdm.observable.asset.CreditNotations.CreditNotationsBuilder;
import cdm.observable.asset.CreditNotations.CreditNotationsBuilderImpl;
import cdm.observable.asset.CreditNotations.CreditNotationsImpl;
import cdm.observable.asset.MultipleCreditNotations;
import cdm.observable.asset.meta.CreditNotationsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Represents the credit rating notation higher level construct, which provides the ability to specify multiple rating notations.
 * @version ${project.version}
 */
@RosettaDataType(value="CreditNotations", builder=CreditNotations.CreditNotationsBuilderImpl.class, version="${project.version}")
public interface CreditNotations extends RosettaModelObject {

	CreditNotationsMeta metaData = new CreditNotationsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies only one credit notation is determined.
	 */
	CreditNotation getCreditNotation();
	/**
	 * Specifies if several credit notations exist, alongside an &#39;any&#39; or &#39;all&#39; or all condition.
	 */
	MultipleCreditNotations getCreditNotations();

	/*********************** Build Methods  ***********************/
	CreditNotations build();
	
	CreditNotations.CreditNotationsBuilder toBuilder();
	
	static CreditNotations.CreditNotationsBuilder builder() {
		return new CreditNotations.CreditNotationsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CreditNotations> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CreditNotations> getType() {
		return CreditNotations.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("creditNotation"), processor, CreditNotation.class, getCreditNotation());
		processRosetta(path.newSubPath("creditNotations"), processor, MultipleCreditNotations.class, getCreditNotations());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CreditNotationsBuilder extends CreditNotations, RosettaModelObjectBuilder {
		CreditNotation.CreditNotationBuilder getOrCreateCreditNotation();
		CreditNotation.CreditNotationBuilder getCreditNotation();
		MultipleCreditNotations.MultipleCreditNotationsBuilder getOrCreateCreditNotations();
		MultipleCreditNotations.MultipleCreditNotationsBuilder getCreditNotations();
		CreditNotations.CreditNotationsBuilder setCreditNotation(CreditNotation creditNotation);
		CreditNotations.CreditNotationsBuilder setCreditNotations(MultipleCreditNotations creditNotations);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("creditNotation"), processor, CreditNotation.CreditNotationBuilder.class, getCreditNotation());
			processRosetta(path.newSubPath("creditNotations"), processor, MultipleCreditNotations.MultipleCreditNotationsBuilder.class, getCreditNotations());
		}
		

		CreditNotations.CreditNotationsBuilder prune();
	}

	/*********************** Immutable Implementation of CreditNotations  ***********************/
	class CreditNotationsImpl implements CreditNotations {
		private final CreditNotation creditNotation;
		private final MultipleCreditNotations creditNotations;
		
		protected CreditNotationsImpl(CreditNotations.CreditNotationsBuilder builder) {
			this.creditNotation = ofNullable(builder.getCreditNotation()).map(f->f.build()).orElse(null);
			this.creditNotations = ofNullable(builder.getCreditNotations()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("creditNotation")
		public CreditNotation getCreditNotation() {
			return creditNotation;
		}
		
		@Override
		@RosettaAttribute("creditNotations")
		public MultipleCreditNotations getCreditNotations() {
			return creditNotations;
		}
		
		@Override
		public CreditNotations build() {
			return this;
		}
		
		@Override
		public CreditNotations.CreditNotationsBuilder toBuilder() {
			CreditNotations.CreditNotationsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CreditNotations.CreditNotationsBuilder builder) {
			ofNullable(getCreditNotation()).ifPresent(builder::setCreditNotation);
			ofNullable(getCreditNotations()).ifPresent(builder::setCreditNotations);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditNotations _that = getType().cast(o);
		
			if (!Objects.equals(creditNotation, _that.getCreditNotation())) return false;
			if (!Objects.equals(creditNotations, _that.getCreditNotations())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditNotation != null ? creditNotation.hashCode() : 0);
			_result = 31 * _result + (creditNotations != null ? creditNotations.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditNotations {" +
				"creditNotation=" + this.creditNotation + ", " +
				"creditNotations=" + this.creditNotations +
			'}';
		}
	}

	/*********************** Builder Implementation of CreditNotations  ***********************/
	class CreditNotationsBuilderImpl implements CreditNotations.CreditNotationsBuilder {
	
		protected CreditNotation.CreditNotationBuilder creditNotation;
		protected MultipleCreditNotations.MultipleCreditNotationsBuilder creditNotations;
	
		public CreditNotationsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("creditNotation")
		public CreditNotation.CreditNotationBuilder getCreditNotation() {
			return creditNotation;
		}
		
		@Override
		public CreditNotation.CreditNotationBuilder getOrCreateCreditNotation() {
			CreditNotation.CreditNotationBuilder result;
			if (creditNotation!=null) {
				result = creditNotation;
			}
			else {
				result = creditNotation = CreditNotation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("creditNotations")
		public MultipleCreditNotations.MultipleCreditNotationsBuilder getCreditNotations() {
			return creditNotations;
		}
		
		@Override
		public MultipleCreditNotations.MultipleCreditNotationsBuilder getOrCreateCreditNotations() {
			MultipleCreditNotations.MultipleCreditNotationsBuilder result;
			if (creditNotations!=null) {
				result = creditNotations;
			}
			else {
				result = creditNotations = MultipleCreditNotations.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("creditNotation")
		public CreditNotations.CreditNotationsBuilder setCreditNotation(CreditNotation creditNotation) {
			this.creditNotation = creditNotation==null?null:creditNotation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("creditNotations")
		public CreditNotations.CreditNotationsBuilder setCreditNotations(MultipleCreditNotations creditNotations) {
			this.creditNotations = creditNotations==null?null:creditNotations.toBuilder();
			return this;
		}
		
		@Override
		public CreditNotations build() {
			return new CreditNotations.CreditNotationsImpl(this);
		}
		
		@Override
		public CreditNotations.CreditNotationsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditNotations.CreditNotationsBuilder prune() {
			if (creditNotation!=null && !creditNotation.prune().hasData()) creditNotation = null;
			if (creditNotations!=null && !creditNotations.prune().hasData()) creditNotations = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCreditNotation()!=null && getCreditNotation().hasData()) return true;
			if (getCreditNotations()!=null && getCreditNotations().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditNotations.CreditNotationsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CreditNotations.CreditNotationsBuilder o = (CreditNotations.CreditNotationsBuilder) other;
			
			merger.mergeRosetta(getCreditNotation(), o.getCreditNotation(), this::setCreditNotation);
			merger.mergeRosetta(getCreditNotations(), o.getCreditNotations(), this::setCreditNotations);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditNotations _that = getType().cast(o);
		
			if (!Objects.equals(creditNotation, _that.getCreditNotation())) return false;
			if (!Objects.equals(creditNotations, _that.getCreditNotations())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditNotation != null ? creditNotation.hashCode() : 0);
			_result = 31 * _result + (creditNotations != null ? creditNotations.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditNotationsBuilder {" +
				"creditNotation=" + this.creditNotation + ", " +
				"creditNotations=" + this.creditNotations +
			'}';
		}
	}
}
