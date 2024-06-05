package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.Equity;
import cdm.base.staticdata.asset.common.Equity.EquityBuilder;
import cdm.base.staticdata.asset.common.Equity.EquityBuilderImpl;
import cdm.base.staticdata.asset.common.Equity.EquityImpl;
import cdm.base.staticdata.asset.common.IdentifiedProduct;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductBuilder;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductBuilderImpl;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.meta.EquityMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * A class to specify an equity as having a product identifier. As a difference versus the FpML standard, the CDM structure of this class only includes the productIdentifier class, which consists of an identifier, productTaxonomy, and source of the identifier. The reason for this approach is to avoid the potential for conflicting information between the information associated with the contractual product and the reference information maintained by the relevant service provider.
 * @version ${project.version}
 */
@RosettaDataType(value="Equity", builder=Equity.EquityBuilderImpl.class, version="${project.version}")
public interface Equity extends IdentifiedProduct {

	EquityMeta metaData = new EquityMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	Equity build();
	
	Equity.EquityBuilder toBuilder();
	
	static Equity.EquityBuilder builder() {
		return new Equity.EquityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Equity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Equity> getType() {
		return Equity.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.class, getProductIdentifier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EquityBuilder extends Equity, IdentifiedProduct.IdentifiedProductBuilder, RosettaModelObjectBuilder {
		Equity.EquityBuilder setProductIdentifier(ProductIdentifier productIdentifier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getProductIdentifier());
		}
		

		Equity.EquityBuilder prune();
	}

	/*********************** Immutable Implementation of Equity  ***********************/
	class EquityImpl extends IdentifiedProduct.IdentifiedProductImpl implements Equity {
		
		protected EquityImpl(Equity.EquityBuilder builder) {
			super(builder);
		}
		
		@Override
		public Equity build() {
			return this;
		}
		
		@Override
		public Equity.EquityBuilder toBuilder() {
			Equity.EquityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Equity.EquityBuilder builder) {
			super.setBuilderFields(builder);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "Equity {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Equity  ***********************/
	class EquityBuilderImpl extends IdentifiedProduct.IdentifiedProductBuilderImpl  implements Equity.EquityBuilder {
	
	
		public EquityBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("productIdentifier")
		public Equity.EquityBuilder setProductIdentifier(ProductIdentifier productIdentifier) {
			this.productIdentifier = productIdentifier==null?null:productIdentifier.toBuilder();
			return this;
		}
		
		@Override
		public Equity build() {
			return new Equity.EquityImpl(this);
		}
		
		@Override
		public Equity.EquityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Equity.EquityBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Equity.EquityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Equity.EquityBuilder o = (Equity.EquityBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquityBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
