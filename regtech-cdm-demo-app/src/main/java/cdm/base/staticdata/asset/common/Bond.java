package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.Bond;
import cdm.base.staticdata.asset.common.Bond.BondBuilder;
import cdm.base.staticdata.asset.common.Bond.BondBuilderImpl;
import cdm.base.staticdata.asset.common.Bond.BondImpl;
import cdm.base.staticdata.asset.common.IdentifiedProduct;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductBuilder;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductBuilderImpl;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.meta.BondMeta;
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
 * A class to specify a bond as having a product identifier. As a difference versus the FpML standard, the CDM structure of this class only includes the productIdentifier class, which consists of an identifier, productTaxonomy, and source of the identifier. The reason for this approach is to avoid the potential for conflicting information between the information associated with the contractual product and the reference information maintained by the relevant service provider.
 * @version ${project.version}
 */
@RosettaDataType(value="Bond", builder=Bond.BondBuilderImpl.class, version="${project.version}")
public interface Bond extends IdentifiedProduct {

	BondMeta metaData = new BondMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	Bond build();
	
	Bond.BondBuilder toBuilder();
	
	static Bond.BondBuilder builder() {
		return new Bond.BondBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Bond> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Bond> getType() {
		return Bond.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.class, getProductIdentifier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BondBuilder extends Bond, IdentifiedProduct.IdentifiedProductBuilder, RosettaModelObjectBuilder {
		Bond.BondBuilder setProductIdentifier(ProductIdentifier productIdentifier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getProductIdentifier());
		}
		

		Bond.BondBuilder prune();
	}

	/*********************** Immutable Implementation of Bond  ***********************/
	class BondImpl extends IdentifiedProduct.IdentifiedProductImpl implements Bond {
		
		protected BondImpl(Bond.BondBuilder builder) {
			super(builder);
		}
		
		@Override
		public Bond build() {
			return this;
		}
		
		@Override
		public Bond.BondBuilder toBuilder() {
			Bond.BondBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Bond.BondBuilder builder) {
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
			return "Bond {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Bond  ***********************/
	class BondBuilderImpl extends IdentifiedProduct.IdentifiedProductBuilderImpl  implements Bond.BondBuilder {
	
	
		public BondBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("productIdentifier")
		public Bond.BondBuilder setProductIdentifier(ProductIdentifier productIdentifier) {
			this.productIdentifier = productIdentifier==null?null:productIdentifier.toBuilder();
			return this;
		}
		
		@Override
		public Bond build() {
			return new Bond.BondImpl(this);
		}
		
		@Override
		public Bond.BondBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Bond.BondBuilder prune() {
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
		public Bond.BondBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Bond.BondBuilder o = (Bond.BondBuilder) other;
			
			
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
			return "BondBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
